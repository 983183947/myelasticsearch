package com.tch.index.quartz;

import com.alibaba.druid.pool.DruidDataSource;
import com.tch.dao.SearchRepository;
import com.tch.domain.TElDbEntity;
import com.tch.domain.TElSearchEntity;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * User: zzj
 * Date: 2017-09-25
 * Time: 15:30
 */
@Component
public class JobIndexdao {

    @Autowired
    SearchRepository searchRepository;

    @Async
    public Future<String> creatIndexByOid(TElSearchEntity searchEntity) throws InterruptedException {
        //设置状态为执行中
        searchEntity.setSqlFinish("0");
        searchRepository.saveAndFlush(searchEntity);
        QueryRunner queryRunner=getQueryRunner(searchEntity);

        return new AsyncResult<String>("task1执行完毕");
    }

    /**
     *根据TElDbEntity获取QueryRunner用于查数据库信息
     * @param searchEntity
     */
    public QueryRunner getQueryRunner(TElSearchEntity searchEntity){
        TElDbEntity dbEntity = searchEntity.gettElDbEntity();
        DruidDataSource druidDataSource=new DruidDataSource();
        String url=dbEntity.getJdbcUrl();
        if(StringUtils.isEmpty(url)){
            url="jdbc:oracle:thin:@"+dbEntity.getJdkip()+":"+dbEntity.getJdkport()+":"+dbEntity.getJdksid();
        }
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(dbEntity.getJdkuser());
        druidDataSource.setPassword(dbEntity.getJdkpass());
        QueryRunner queryRunner =new QueryRunner(druidDataSource);
        return queryRunner;
    }

}
