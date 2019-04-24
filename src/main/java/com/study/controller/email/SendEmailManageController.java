package com.study.controller.email;

import com.study.orm.Email;
import com.study.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//围绕两个问题进行处理
//1.如何向不同的邮箱服务器发送邮件
//2.如何接收不同的邮箱服务器发来的邮件
@RestController
@RequestMapping("email")
public class SendEmailManageController {
    //需要两张表,一张记录邮件,一张记录邮件的相关协议

    @Autowired
    private EmailService emailService;

    @RequestMapping("fetchList")
    public String fetchList() {
        Map<String, Object> query = new HashMap<>();
        List<Email> emails = emailService.fetchList(query);
        if (!CollectionUtils.isEmpty(emails)) {
            for (Email email : emails) {
                System.out.println(email.getId());
            }
        }
        return "Success";
    }

    @RequestMapping("fetchNextId")
    public String fetchNextId() {
        System.out.println(emailService.fetchNextId());
        return "success";
    }

    @RequestMapping("sendEmail")
    public String sendEmail(){
        List<String> filenames = new ArrayList<>();
        filenames.add("E:\\360MoveData\\Users\\12\\Desktop\\attachment.txt");
        filenames.add("E:\\360MoveData\\Users\\12\\Desktop\\log.txt");
        emailService.sendEmail("mengling_wang@qq.com","3002832714@qq.com","Test","Test",filenames);
        return "success";
    }
}
