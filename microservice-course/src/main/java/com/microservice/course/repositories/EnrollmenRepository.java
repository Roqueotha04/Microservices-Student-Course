package com.microservice.course.repositories;

import com.microservice.course.entities.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmenRepository extends JpaRepository<Enrollment, Long> {
}
