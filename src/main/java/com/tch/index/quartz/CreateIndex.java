package com.tch.index.quartz;

import com.tch.dao.SearchRepository;
import com.tch.domain.TElSearchEntity;
import org.apache.commons.collections4.CollectionUtils;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

/**
 * 创建索引定时器
 * User: zzj
 * Date: 2017-09-21
 * Time: 16:10
 */
public class CreateIndex extends QuartzJobBean {
    @Value("${index.creaThread}")
    private int message = 5;

    @Autowired
    SearchRepository searchRepository;
    @Autowired
    JobIndexservice jobIndexdao;


    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext){

        List<TElSearchEntity> searchEntities = searchRepository.findAllBySqlFinishAndSStop("1","否 ");
        System.out.println("暂时没有对象..."+ searchEntities.size());
        if(CollectionUtils.isEmpty(searchEntities)){

            return;
        }

        List<Future<String>> retList=new ArrayList<Future<String>>();
        for(int i=0;i<message && i<searchEntities.size();i++){
            try {
                Future<String> done = jobIndexdao.creatIndexByOid(searchEntities.get(i));
                retList.add(done);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        //等线程结束再开启下一轮
        try {
            asynDone(retList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void asynDone(List<Future<String>> retlist) throws InterruptedException {
        for(int i=0;i<retlist.size();i++){
            if(!retlist.get(i).isDone()){
                Thread.sleep(1000);
                asynDone(retlist);
                return;
            }
        }
    }

}
