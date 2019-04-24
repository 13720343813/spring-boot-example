package com.study.service;

import com.study.orm.Attachment;

import java.util.List;

public interface AttachmentService {
    List<Attachment> fetchByEmailId(Integer emailId);
}
