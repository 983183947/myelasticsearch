package com.tch.index.quartz;

import com.alibaba.druid.pool.DruidDataSource;
import com.tch.dao.SearchRepository;
import com.tch.domain.TElDbEntity;
import com.tch.domain.TElSearchEntity;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @author: zzj
 * @date: 2017/11/13
 * Time: 13:31
 */
@Repository
public class JobIndexdao {
    @Autowired
    SearchRepository searchRepository;

    /**
     * 生成索引
     * @param queryRunner
     */
    public void creatIndex(QueryRunner queryRunner,TElSearchEntity searchEntity){
        if(StringUtils.equals(searchEntity.getSzlMode(),"每次全量")){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 根据TElDbEntity获取QueryRunner用于查数据库信息
     *
     * @param searchEntity
     */
    public QueryRunner getQueryRunner(TElSearchEntity searchEntity) {
        TElDbEntity dbEntity = searchEntity.gettElDbEntity();
        DruidDataSource druidDataSource = new DruidDataSource();
        String url = dbEntity.getJdbcUrl();
        if (StringUtils.isEmpty(url)) {
            url = "jdbc:oracle:thin:@" + dbEntity.getJdkip() + ":" + dbEntity.getJdkport() + ":" + dbEntity.getJdksid();
        }
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(dbEntity.getJdkuser());
        druidDataSource.setPassword(dbEntity.getJdkpass());
        QueryRunner queryRunner = new QueryRunner(druidDataSource);
        return queryRunner;
    }

    /**
     * 查看是否需要生成索引
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
                    return false;
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
     * @param sat
     * @param searchEntity
     */
    public void setStat(String sat,TElSearchEntity searchEntity){
        searchEntity.setSqlFinish(sat);
        searchRepository.saveAndFlush(searchEntity);
    }
}
