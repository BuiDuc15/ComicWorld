package com.comicworld.layer.domain.service.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.Map;

@Service("gmailServiceImplV1")
public class GmailServiceImplV1 implements MailService {

    @Value("${app.mail}")
    private String APP_MAIL;

    @Autowired
    private JavaMailSender sender;

    @Override
    @Async
    public void sendMail(Map<String, Object> attributes) {

        MimeMessage message = this.sender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message);

        String to = (String) attributes.get("to");

        String content = (String) attributes.get("content");

        String subject = (String) attributes.get("subject");

        String personal = (String) attributes.get("personal");

        try {
            helper.setFrom(this.APP_MAIL, personal);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            this.sender.send(message);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}











































