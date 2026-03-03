package com.microservice.email.dto;

public record NotificationRequest(String email, String subject, String body) {
}
