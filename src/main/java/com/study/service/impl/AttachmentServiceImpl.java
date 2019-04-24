package com.study.service.impl;

import com.study.mapper.AttachmentMapper;
import com.study.orm.Attachment;
import com.study.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AttachmentServiceImpl implements AttachmentService {
    @Autowired
    private AttachmentMapper attachmentMapper;

    @Override
    public List<Attachment> fetchByEmailId(Integer emailId) {
        return attachmentMapper.fetchByEmailId(emailId);
    }
}
