package com.microservice.email.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService{

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String content) {

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(to);
            helper.setSubject(subject);

            String htmlContent = createEmailMessage(content);


            helper.setText(htmlContent, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Error sending email", e);
        }
    }

    public String createEmailMessage(String content){
        return "<html><body style='font-family: Arial, sans-serif;'>" +
                "<h1 style='color: #2e6c80;'>¡Bienvenido al curso!</h1>" +
                "<p>" + content + "</p>" +
                "<footer style='margin-top: 20px; border-top: 1px solid #ccc;'>Soporte Académico</footer>" +
                "</body></html>";
    }


}
