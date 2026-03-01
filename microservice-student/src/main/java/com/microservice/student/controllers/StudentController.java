package com.microservice.student.controllers;

import com.microservice.student.entities.Student;
import com.microservice.student.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveStudent(@RequestBody Student student){
        studentService.save(student);
    }

    @GetMapping("")
    public ResponseEntity<?> findAllStudents(){
        return ResponseEntity.ok(studentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return ResponseEntity.ok(studentService.findById(id));
    }

    @GetMapping("/findByCourse/{id}")
    public ResponseEntity<?> findByCourseId(@PathVariable Long id){
        return ResponseEntity.ok(studentService.findByCourseId(id));
    }


}
