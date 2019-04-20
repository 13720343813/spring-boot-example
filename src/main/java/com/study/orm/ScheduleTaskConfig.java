package com.study.orm;

public class ScheduleTaskConfig {
    private Integer id;
    private String name;
    private String task;
    private String cron;

    public ScheduleTaskConfig() {
    }

    public ScheduleTaskConfig(Integer id, String name, String task, String cron) {
        this.id = id;
        this.name = name;
        this.task = task;
        this.cron = cron;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }
}
