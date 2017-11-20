package com.tch.index.quartz;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSON;
import com.tch.dao.SearchRepository;
import com.tch.domain.TElDbEntity;
import com.tch.domain.TElFieldEntity;
import com.tch.domain.TElSearchEntity;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.*;

/**
 * @author: zzj
 * @date: 2017/11/13
 * Time: 13:31
 */
@Repository
public class JobIndexdao {
    @Autowired
    SearchRepository searchRepository;
    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    QueryRunner queryRunner;
    TElSearchEntity searchEntity;
    String zlField = "";  //增量字段名称
    String fieldType = "";//增量字段类型
    String primaryfield = "";//主键字段
    String zlValue = "";//建立索引时，当前增量值

    /**
     * 生成索引
     *
     * @param queryRunner
     */
    public void creatIndex(QueryRunner queryRunner, TElSearchEntity searchEntity) throws SQLException {
        this.queryRunner = queryRunner;
        this.searchEntity = searchEntity;
        IndexResponse response = null;
        Client client = elasticsearchTemplate.getClient();
        System.out.println(searchEntity.getoName() + "正在生成索引...");
        Set<TElFieldEntity> fieldEntities = searchEntity.gettElFieldEntities();
        StringBuffer sql = new StringBuffer();
        StringBuffer sqlcol = new StringBuffer();
        for (TElFieldEntity fieldEntity : fieldEntities) {
            if (StringUtils.equals(fieldEntity.getAddBig(), "1")) {
                sqlcol.append(fieldEntity.getSfield() + ",");
            }
        }
        String sqlcolstr = StringUtils.substring(sqlcol.toString(), 0, sqlcol.length() - 1);
        sql.append("select " + sqlcolstr + " from " + searchEntity.gettOwner() + "." + searchEntity.gettName());
        if (StringUtils.equals(searchEntity.getSzlMode(), "增量")) {
            if (StringUtils.isNotEmpty(searchEntity.getSzlField()) && StringUtils.isNotEmpty(searchEntity.getSzlValue())) {
                sql.append(" where ");
                /**
                 * 获取增量字段类型
                 */
                for (TElFieldEntity fieldEntity : fieldEntities) {
                    if (StringUtils.equals(fieldEntity.getSfield(), searchEntity.getSzlField())) {
                        fieldType = fieldEntity.getStype();
                        zlField = fieldEntity.getSfield();
                    }
                }

                if (StringUtils.equalsIgnoreCase("NUMBER", fieldType)) {
                    sql.append(" " + searchEntity.getSzlField() + " >= " + searchEntity.getSzlValue());
                } else if (StringUtils.equalsIgnoreCase("DATE", fieldType)) {
                    sql.append(" " + searchEntity.getSzlField() + " <=to_date('" + searchEntity.getSzlValue() + "','yyyy-mm-dd hh24:mi:ss')");
                } else if (StringUtils.equalsIgnoreCase("VARCHAR2", fieldType)) {
                    sql.append(" " + searchEntity.getSzlField() + " >= " + searchEntity.getSzlValue());
                } else {
                    sql.append(" " + searchEntity.getSzlField() + " >= " + searchEntity.getSzlValue());
                }
                sql.append(" order by " + searchEntity.getSzlField());
            }

        }

        /**
         * 获取主键字段
         */
        for (TElFieldEntity fieldEntity : fieldEntities) {
            if (StringUtils.equals(fieldEntity.getIskey(), "1")) {
                primaryfield = fieldEntity.getSfield();
            }
        }

        List<Map<String, Object>> mapList = queryRunner.query(sql.toString(), new MapListHandler());
        System.out.println(mapList.size());
        BulkProcessor bulkProcessor = getBulkProcessor(client);
        for (Map<String, Object> docMap : mapList) {
            //合并大字段
            String doc_content_big = getBigContent(docMap, fieldEntities);
            fetchZlValue(docMap.get(zlField));
            docMap.put("doc_content_big", doc_content_big);
            String json = JSON.toJSONString(docMap);
            //  System.out.println(json+"  "+primaryfield);
            IndexRequest indexRequest = new IndexRequest(searchEntity.getoName(), searchEntity.gettName(), docMap.get(primaryfield).toString()).source(json, XContentType.JSON);
            bulkProcessor.add(indexRequest);
        }
        //将剩余的请求处理完，关闭bulkProcessor
        bulkProcessor.flush();
        // 刷新目录
        client.admin().indices().prepareRefresh().get();

    }

    /**
     * 合并成大字段
     *
     * @param docMap
     * @param fieldEntities
     * @return
     */
    private String getBigContent(Map<String, Object> docMap, Set<TElFieldEntity> fieldEntities) {
        StringBuilder bigCont = new StringBuilder();
        Iterator<Map.Entry<String, Object>> dociterator = docMap.entrySet().iterator();

        while (dociterator.hasNext()) {
            Map.Entry<String, Object> entry = dociterator.next();
            Iterator<TElFieldEntity> fielditerator = fieldEntities.iterator();
            while (fielditerator.hasNext()) {
                TElFieldEntity field = fielditerator.next();
                if (StringUtils.equalsIgnoreCase(field.getSfield(), entry.getKey())) {
                    if (StringUtils.equalsIgnoreCase("1", field.getAddBig())) {
                        bigCont.append(entry.getValue());
                    }
                }
            }
        }
        return bigCont.toString();
    }

    /**
     * 根据TElDbEntity获取QueryRunner用于查数据库信息
     *
     * @param searchEntity
     */
    public QueryRunner getQueryRunner(TElSearchEntity searchEntity) {
        String url = "";
        try {
            TElDbEntity dbEntity = searchEntity.gettElDbEntity();
            DruidDataSource druidDataSource = new DruidDataSource();
            url = dbEntity.getJdbcUrl();
            if (StringUtils.isEmpty(url)) {
                url = "jdbc:oracle:thin:@" + dbEntity.getJdkip() + ":" + dbEntity.getJdkport() + ":" + dbEntity.getJdksid();
            }

            druidDataSource.setUrl(url);
            druidDataSource.setUsername(dbEntity.getJdkuser());
            druidDataSource.setPassword(dbEntity.getJdkpass());
            QueryRunner queryRunner = new QueryRunner(druidDataSource);
            return queryRunner;
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("数据库连接异常：" + url);
        return null;
    }

    /**
     * 查看是否需要生成索引
     *
     * @param searchEntity
     * @return
     */
    public boolean needCreatIndex(TElSearchEntity searchEntity) {
        Date lastTime = searchEntity.getLastTime();
        if (lastTime == null && 0 != searchEntity.getTimertype()) {
            searchEntity.setLastTime(new Date());
            searchRepository.saveAndFlush(searchEntity);
            return true;
        }

        switch (searchEntity.getTimertype()) {
            /**
             * 现在一次
             */
            case 1:
                searchEntity.setTimertype(0);
                searchRepository.saveAndFlush(searchEntity);
                break;
            /**
             * 每小时一次
             */
            case 3:
                if (System.currentTimeMillis() - lastTime.getTime() < (1000 * 60 * 60)) {
                    System.out.println("时间没到一周呢");
                    return false;
                }else{
                    System.out.println("一周了，开干");
                }
                break;
            /**
             * 每天一次
             */
            case 4:
                if (System.currentTimeMillis() - lastTime.getTime() < (1000 * 60 * 60 * 24)) {
                    return false;
                }
                break;
            /**
             * 每周一次
             */
            case 5:
                if (System.currentTimeMillis() - lastTime.getTime() < (1000 * 60 * 60 * 24 * 7)) {
                    return false;
                }
                break;
            /**
             * 每月一次
             */
            case 6:
                if (System.currentTimeMillis() - lastTime.getTime() < (1000 * 60 * 60 * 24 * 30)) {
                    return false;
                }
                break;
        }

        return true;
    }

    /**
     * 对象状态设置
     *
     * @param sat
     * @param searchEntity
     */
    public void setStat(String sat, TElSearchEntity searchEntity) {
        searchEntity.setSqlFinish(sat);
        searchRepository.saveAndFlush(searchEntity);
    }

    private BulkProcessor getBulkProcessor(Client client) {
        BulkProcessor bulkProcessor = BulkProcessor.builder(client, new BulkProcessor.Listener() {
            @Override
            public void beforeBulk(long executionId, BulkRequest request) {
                System.out.println("提交建索引前");
            }

            @Override
            public void afterBulk(long executionId, BulkRequest request, BulkResponse response) {
               if(StringUtils.equals(searchEntity.getSzlMode(), "增量")){
                   String sql = "update " + searchEntity.gettOwner() + "." + searchEntity.gettName() + " set " + searchEntity.getSzlField() + "='" + zlValue + "'";
                   try {
                       queryRunner.update(sql);
                       System.out.println("提交建索引后更新增量值 " + zlValue);
                   } catch (SQLException e) {
                       e.printStackTrace();
                   }
               }
                System.out.println(searchEntity.getoName()+"索引建立完成！");
            }

            @Override
            public void afterBulk(long executionId, BulkRequest request, Throwable failure) {
//                  This method is called when the bulk failed and raised a Throwable
                System.out.println("创建异常" + failure.getMessage());
            }
        })
                //每一千条提交一次
                .setBulkActions(10000)
                //数据量达到5M时提交
                .setBulkSize(new ByteSizeValue(5, ByteSizeUnit.MB))
                //五秒时提交一次
                .setFlushInterval(TimeValue.timeValueSeconds(5))
                //开五个线程提交
                .setConcurrentRequests(5)
                .build();
        return bulkProcessor;
    }

    /**
     * 获取增量值
     * @param o
     */
    private void fetchZlValue(Object o) {
        if (o != null) {
            zlValue=o.toString();
        }else{
            if (StringUtils.equalsIgnoreCase("NUMBER", fieldType)) {
                zlValue="0";
            } else if (StringUtils.equalsIgnoreCase("DATE", fieldType)) {
                zlValue = "1970-01-01 00:00:00";
            } else {
                zlValue="";
            }
        }
    }
}
