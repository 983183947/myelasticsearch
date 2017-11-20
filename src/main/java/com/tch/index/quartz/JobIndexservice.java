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
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.Future;

/**
 * User: zzj
 * Date: 2017-09-25
 * Time: 15:30
 */
@Service
public class JobIndexservice {

    @Autowired
    SearchRepository searchRepository;
    @Autowired
    JobIndexdao jobIndexdao;

    @Async
    public Future<String> creatIndexByOid(TElSearchEntity searchEntity) throws InterruptedException {
        /**
         * 设置状态为执行中
         */
        jobIndexdao.setStat("0",searchEntity);
        /**
         *查看是否需要建立索引
         */
        boolean needCreatIndex=jobIndexdao.needCreatIndex(searchEntity);
        //System.out.println("是否需要产生索引"+needCreatIndex);
        if(!needCreatIndex){
            jobIndexdao.setStat("1",searchEntity);
            return new AsyncResult<String>(searchEntity.getoName()+"不需要生成！");
        }
        /**
         *生成索引
         */
        QueryRunner queryRunner=jobIndexdao.getQueryRunner(searchEntity);
        if(queryRunner == null){
            return new AsyncResult<String>(searchEntity.getoName()+"数据库连接异常,索引生成执行完毕");
        }
        try{
            jobIndexdao.creatIndex(queryRunner,searchEntity);
            searchEntity.setLastTime(new Date());
            searchRepository.save(searchEntity);
        }catch (Exception e){
            e.printStackTrace();
        }
        jobIndexdao.setStat("1",searchEntity);
        return new AsyncResult<String>(searchEntity.getoName()+"索引生成执行完毕");
    }



}
