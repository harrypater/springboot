package com.imooc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class MailService {

    @Value("${spring.mail.username}")
    private  String from;

    @Autowired
    private JavaMailSender  mailSender;

    private   final Logger logger= LoggerFactory.getLogger(this.getClass());


          public  void  sendSimpleMail(String to,String subject,String content){                //发送简单邮件

              SimpleMailMessage message=new SimpleMailMessage();
              message.setTo(to);
              message.setSubject(subject);
              message.setText(content);
              message.setFrom(from);

              mailSender.send(message);
          }
                                                                                               //发送html邮件
    public  void  sendHtmlMail(String to,String subject,String content) {

        logger.info("mial start: {}",subject);
        MimeMessage message=mailSender.createMimeMessage();
        MimeMessageHelper helper= null;
        try {
            helper = new MimeMessageHelper(message,true);
            helper.setTo(to);
            helper.setFrom(from);
            helper.setSubject(subject);
            helper.setText(content,true);
        } catch (MessagingException e) {
            logger.info("发送邮件失败",e);
        }

          mailSender.send(message);

    }
                                                                                              //发送附件邮件
     public  void  sendAttachMail(String to,String subject,String content,String filePath) throws MessagingException {

         MimeMessage message=mailSender.createMimeMessage();
         MimeMessageHelper helper=new MimeMessageHelper(message,true);
         helper.setTo(to);
         helper.setFrom(from);
         helper.setSubject(subject);
         helper.setText(content,true);

         FileSystemResource file=new FileSystemResource(new File(filePath));
         String filename=file.getFilename();
         helper.addAttachment(filename,file);

         mailSender.send(message);
     }
                                                                                     //内联资源
    public  void  sendInlineResoureMail(String to,String subject,String content,String rscPath,String rscId) throws MessagingException {


        MimeMessage message=mailSender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(message,true);
        helper.setTo(to);
        helper.setFrom(from);
        helper.setSubject(subject);
        helper.setText(content,true);

        FileSystemResource res=new FileSystemResource(new File(rscPath));
        helper.addInline(rscId,res);
        mailSender.send(message);
    }


}
