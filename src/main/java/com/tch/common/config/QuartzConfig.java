package com.tch.common.config;

import com.tch.index.quartz.CreateIndex;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * User: zzj
 * Date: 2017-09-20
 * Time: 16:27
 */
@Configuration
public class QuartzConfig {
    @Bean
    public JobDetail createIndexJobDetail() {
        return JobBuilder.newJob(CreateIndex.class).withIdentity("createIndexJob").storeDurably().build();
    }
    @Bean
    public Trigger createIndexJobTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(2000000).repeatForever();

        return TriggerBuilder.newTrigger().forJob(createIndexJobDetail())
                .withIdentity("createIndexTrigger").withSchedule(scheduleBuilder).build();
    }

}
