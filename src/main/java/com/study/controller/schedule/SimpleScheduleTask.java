package com.study.controller.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SimpleScheduleTask {
    @Scheduled(cron = "0/10 * * * * ?")
    public void doSimpleSchedule(){
        System.out.println("do scheduled task : " + new Date());
    }
}
