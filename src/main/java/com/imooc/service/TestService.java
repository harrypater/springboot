package com.imooc.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.MessagingException;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestService {

    @Resource
    private MailService mailService;

    @Resource
    TemplateEngine templateEngine;


    @Test
    public void sendSimpleMail() {
        mailService.sendSimpleMail("harrypater02@163.com", "two mail", "hello zeep!");
    }

    @Test
    public void sendHtmlMail() throws MessagingException {

        String content = "<html>\n" +
                "<body>\n" +
                "<h3> hello world</h3>\n" +
                "</body>\n" +
                "</html>";

        mailService.sendHtmlMail("harrypater03@163.com", "html mial", content);

    }

    @Test
    public void sendAttachMail() throws MessagingException {

        String filePath = "C:\\Users\\ASUS\\Desktop\\demo.txt";
        mailService.sendAttachMail("harrypater03@163.com", "attach mail", "this is demo.txt", filePath);
    }

    @Test
    public void sendInlineResourceMail() throws MessagingException {

        String imgRes = "C:\\Users\\ASUS\\Desktop\\3a.jpg";
        String srcId = "neo_001";
        String content = "<html><body>picture:" +
                "<img src=\'cid:" + srcId + "\'></img>" +
                "<img src=\'cid:" + srcId + "\'></img>" +
                "</body></html>";
        mailService.sendInlineResoureMail("harrypater03@163.com", "attach mail", content, imgRes, srcId);
    }

    @Test                                                           //发送模板邮件
    public void sendTmplateMail() {

               Context context=new Context();
               context.setVariable("id","007");

               String emailContent=templateEngine.process("emilTemplates",context);
               mailService.sendHtmlMail("harrypater03@163.com","templateMail",emailContent);

    }

}