package com.study.service;

import com.study.orm.ScheduleTaskConfig;

import java.util.List;

public interface ScheduleTaskConfigService {
    List<ScheduleTaskConfig> fetchList();
}
