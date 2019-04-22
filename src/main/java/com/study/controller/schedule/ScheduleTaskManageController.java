package com.study.controller.schedule;

import com.mchange.v1.util.MapUtils;
import com.study.orm.ScheduleTaskConfig;
import com.study.service.ScheduleTaskConfigService;
import com.study.utils.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

@RequestMapping("schedule")
@RestController
public class ScheduleTaskManageController {

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @Bean
    public ThreadPoolTaskScheduler getThreadPoolTaskScheduler() {
        return new ThreadPoolTaskScheduler();
    }

    private Map<Integer, ScheduledFuture> scheduledFutureMap = new HashMap<>();
    @Autowired
    private ScheduleTaskConfigService scheduleTaskConfigService;

    public void _init(){
        System.out.println("++++++++++++init++++++++++++++++");
        List<ScheduleTaskConfig> scheduleTaskConfigs = scheduleTaskConfigService.fetchList();
        if (!CollectionUtils.isEmpty(scheduleTaskConfigs)) {
            for (ScheduleTaskConfig scheduleTaskConfig : scheduleTaskConfigs) {
                ScheduledFuture future = threadPoolTaskScheduler.schedule(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            System.out.println(scheduleTaskConfig.getTask());
                            Process process = Runtime.getRuntime().exec(scheduleTaskConfig.getTask());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Trigger() {
                    @Override
                    public Date nextExecutionTime(TriggerContext triggerContext) {
                        return new CronTrigger(scheduleTaskConfig.getCron()).nextExecutionTime(triggerContext);
                    }
                });
                scheduledFutureMap.put(scheduleTaskConfig.getId(), future);
            }
        }
    }
    public void _destory(){
        System.out.println("++++++++++++++++destory++++++++++++");
        if (scheduledFutureMap != null && !scheduledFutureMap.isEmpty()) {
            for (Integer key : scheduledFutureMap.keySet()) {
                System.out.println("scheduledFutureMap :" + key);
                scheduledFutureMap.get(key).cancel(true);
            }
        }
        scheduledFutureMap.clear();
    }
    @RequestMapping("start")
    public String startScheduleTask() {
        _init();
        return "success";
    }

    @RequestMapping("stop")
    public String stopScheduleTask() {
        _destory();
        return "success";
    }
}
