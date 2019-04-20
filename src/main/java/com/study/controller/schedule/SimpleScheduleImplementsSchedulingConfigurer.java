package com.study.controller.schedule;

import com.study.orm.ScheduleTaskConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.config.TriggerTask;
import org.springframework.scheduling.support.CronTrigger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;

@Configuration
@EnableScheduling
public class SimpleScheduleImplementsSchedulingConfigurer implements SchedulingConfigurer {
    private static List<ScheduleTaskConfig> tasks;

    static {
        tasks = Arrays.asList(new ScheduleTaskConfig(1, "test1", "curl \"http://localhost:8080/authenticate/runScript?type=DoSimpleJobTask&args=aa\"", "0 0/1 * * * ?"),
                new ScheduleTaskConfig(2, "test2", "curl \"www.baidu.com\"", "0/5 * * * * ?"));
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        List<TriggerTask> triggerTasks = new ArrayList<>();
        for (ScheduleTaskConfig scheduleTaskConfig : tasks) {
            triggerTasks.add(new TriggerTask(new Runnable() {
                @Override
                public void run() {
                    try {
                        Runtime.getRuntime().exec(scheduleTaskConfig.getTask());
                        System.out.println(Thread.currentThread().getName() + " : " + scheduleTaskConfig.getName() + " ; " + new Date());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, triggerContext -> {
                return new CronTrigger(scheduleTaskConfig.getCron()).nextExecutionTime(triggerContext);
            }));
        }
        scheduledTaskRegistrar.setScheduler(Executors.newScheduledThreadPool(10));
        scheduledTaskRegistrar.setTriggerTasksList(triggerTasks);
    }
}
