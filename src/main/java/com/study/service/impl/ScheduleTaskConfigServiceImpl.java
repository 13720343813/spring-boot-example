package com.study.service.impl;

import com.study.mapper.ScheduleTaskConfigMapper;
import com.study.orm.ScheduleTaskConfig;
import com.study.service.ScheduleTaskConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleTaskConfigServiceImpl implements ScheduleTaskConfigService {
    @Autowired
    private ScheduleTaskConfigMapper scheduleTaskConfigMapper;

    @Override
    public List<ScheduleTaskConfig> fetchList() {
        return scheduleTaskConfigMapper.fetchList();
    }
}
