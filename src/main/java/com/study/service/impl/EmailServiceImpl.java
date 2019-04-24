package com.study.service.impl;

import com.study.mapper.AttachmentMapper;
import com.study.mapper.EmailMapper;
import com.study.orm.Attachment;
import com.study.orm.Email;
import com.study.service.EmailService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private EmailMapper emailMapper;

    @Autowired
    private AttachmentMapper attachmentMapper;

    public List<Email> fetchList(Map<String, Object> query) {
        return emailMapper.fetchList(query);
    }

    public Boolean update(Map<String,Object> updates){
        try {
            emailMapper.update(updates);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean sendEmail(String from, String to, String subject, String content, List<String> filenames) {
        try {
            Email email = new Email();
            email.setSendEmail(from);
            email.setReceiveEmail(to);
            email.setSubject(subject);
            email.setContent(content);
            email.setEmailStatus(0);
            Integer id = fetchNextId();
            email.setId(id);
            for (String filename : filenames) {
                File file = new File(filename);
                String uploadPath = "F:/uploads/";
                String path = uploadPath + "attachment/" + id + "/" + file.getName();
                Attachment attachment = new Attachment();
                attachment.setEmailId(id);
                attachment.setName(file.getName());
                File tmpFile = new File(path);
                FileUtils.copyFile(file,tmpFile);
                //create attachment
                attachmentMapper.insert(attachment);
            }
            //create email
            emailMapper.insert(email);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Integer fetchNextId() {
        return emailMapper.fetchNextId();
    }
}
