package com.study.mapper;

import com.study.orm.ScheduleTaskConfig;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleTaskConfigMapper {
    @Select("select * from tb_task")
    List<ScheduleTaskConfig> fetchList();
}
