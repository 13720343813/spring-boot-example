package com.study.service;

import com.study.orm.Email;

import java.util.List;
import java.util.Map;

public interface EmailService {
    public List<Email> fetchList(Map<String, Object> query);

    public Integer fetchNextId();

    public Boolean update(Map<String,Object> updates);

    boolean sendEmail(String from, String to, String subject, String content, List<String> filenames);
}
