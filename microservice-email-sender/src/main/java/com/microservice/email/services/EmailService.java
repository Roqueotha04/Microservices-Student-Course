package com.microservice.email.services;

public interface EmailService {

    public void sendEmail(String to, String subject, String content);
}
