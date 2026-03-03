package com.microservice.course.http.request;

public record NotificationRequest(String email, String subject, String body) {
}
