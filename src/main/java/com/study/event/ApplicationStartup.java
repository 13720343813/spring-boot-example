package com.study.event;

import com.study.controller.schedule.ScheduleTaskManageController;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationStartedEvent> {
    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
        System.out.println("++++++++++++++application started event+++++++++++++++");
        ScheduleTaskManageController scheduleTaskManageController = applicationStartedEvent.getApplicationContext().getBean(ScheduleTaskManageController.class);
        scheduleTaskManageController._destory();
        scheduleTaskManageController._init();
    }
}
