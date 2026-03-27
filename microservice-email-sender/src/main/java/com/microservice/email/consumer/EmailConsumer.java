package com.microservice.email.consumer;

import com.microservice.email.dto.NotificationRequest;
import com.microservice.email.services.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EmailConsumer {

    @Autowired
    private final EmailService emailService;

    public EmailConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    //Recieves RabbitMQ message
    @RabbitListener(queues = "course.email.queue")
    public void receiveMessage(NotificationRequest request) {
        log.info("Message sent to {}", request.email());
        emailService.sendEmail(request.email(), request.subject(), request.body());
    }
}
