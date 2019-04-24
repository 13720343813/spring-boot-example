package com.study.mapper;

import com.study.orm.Email;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface EmailMapper {
    List<Email> fetchList(Map<String, Object> query);

    int insert(Email email);

    int update(Map<String,Object> updates);

    Integer fetchNextId();
}
