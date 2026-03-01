package com.microservice.course.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


public record StudentDTO(String name, String lastName, String email, Long courseId) {}
