package com.frame.springboot.task;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @Author: railgun
 * 2021/6/27 21:42
 * PS: 测试类
 **/
@SpringBootTest
public class TaskApplicationTests {

    @Autowired
    JavaMailSenderImpl mailSender;

    @Test
    public void test() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("通知-明天来狂神这听课");
        message.setText("今晚7:30开会");

        message.setTo("2354547500@qq.com");
        message.setFrom("2354547500@qq.com");
        mailSender.send(message);
    }

    @Test
    public void test1() throws MessagingException {
        // 邮件设置2：一个复杂的邮件
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        // 文本内容
        helper.setSubject("通知-明天来狂神这听课");
        helper.setText("<b style='color:red'>今天 7:30来开会</b>", true);
        // 发送附件
        helper.addAttachment("1.jpg", new File("D:\\ai39\\image\\伊蕾娜\\1101634.jpg"));
        helper.addAttachment("2.jpg", new File("D:\\ai39\\image\\伊蕾娜\\1114176.jpg"));

        helper.setTo("2354547500@qq.com");
        helper.setFrom("2354547500@qq.com");
        mailSender.send(mimeMessage);
    }

}
