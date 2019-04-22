package com.study.config;

import com.study.controller.schedule.SimpleScheduleExtendsQuartzJobTask;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SimpleScheduleConfig {
    @Bean
    public JobDetail simpleJobBeanDetails() {
        return JobBuilder.newJob(SimpleScheduleExtendsQuartzJobTask.class).withIdentity("simpleJobBean").storeDurably().build();
    }

    @Bean
    public Trigger simpleTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(15).repeatForever();
        return TriggerBuilder.newTrigger().withIdentity("simpleTrigger").forJob(simpleJobBeanDetails()).withSchedule(scheduleBuilder).build();
    }
}
