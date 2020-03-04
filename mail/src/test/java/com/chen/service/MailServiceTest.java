package com.chen.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 邮件服务测试类
 * <p>
 * @Author LeifChen
 * @Date 2020-03-04
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {

    @Autowired
    private MailService mailService;

    @Test
    public void sendMail() {
        String to = "lefengchen@163.com";
        String subject = "Spring Boot Mail";
        String content = "This is designed by LeifChen";
        String filePath = "E:\\file.zip";
        String imagePath = "E:\\avatar.jpg";
        String imageId = "avatar";

        mailService.sendMail(to, subject, content, filePath, imagePath, imageId);
    }
}