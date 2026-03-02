package com.microservice.course.client;

import com.microservice.course.dto.StudentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "msvc-student", url = "localhost:8080/api/students")
public interface StudentClient {

    @GetMapping("/findByCourse/{id}")
    List<StudentDTO> findAllStudentsByCourse(@PathVariable Long id);

    @GetMapping("/{id}")
    StudentDTO findStudentById(@PathVariable Long id);

}
