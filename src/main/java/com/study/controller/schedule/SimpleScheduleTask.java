package com.study.controller.schedule;

import com.study.orm.Attachment;
import com.study.orm.Email;
import com.study.service.AttachmentService;
import com.study.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SimpleScheduleTask {
    private final String PATH = "F:/uploads/attachment/";
    @Autowired
    private EmailService emailService;

    @Autowired
    private AttachmentService attachmentService;

    @Autowired
    private JavaMailSender sender;

    @Scheduled(cron = "0/10 * * * * ?")
    public void doSimpleSchedule() {
        System.out.println("do scheduled task : " + new Date());
    }

    @Scheduled(cron = "0 0/10 * * * ?")
    public void doSendMail() {
        Map<String, Object> query = new HashMap<>();
        query.put("emailStatus", 0);
        List<Email> emails = emailService.fetchList(query);
        Map<String, Object> updates = new HashMap<>();
        if (!CollectionUtils.isEmpty(emails)) {
            for (Email email : emails) {
                try {
                    MimeMessage mimeMessage = sender.createMimeMessage();
                    MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
                    String toEmail = email.getReceiveEmail();
                    String[] tos = toEmail.split("\\,");
                    mimeMessageHelper.setTo(tos);
                    mimeMessageHelper.setFrom(email.getSendEmail());
                    mimeMessageHelper.setSubject(email.getSubject());
                    mimeMessageHelper.setText(email.getContent(), true);
                    List<Attachment> attachments = attachmentService.fetchByEmailId(email.getId());
                    if (!CollectionUtils.isEmpty(attachments)) {
                        for (Attachment attachment : attachments) {
                            String path = PATH + email.getId() + "/" + attachment.getName();
                            System.out.println(path);
                            mimeMessageHelper.addAttachment(attachment.getName(), new File(path));
                        }
                    }
                    sender.send(mimeMessage);
                    updates.clear();
                    updates.put("id", email.getId());
                    updates.put("emailStatus", 1);
                    emailService.update(updates);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
