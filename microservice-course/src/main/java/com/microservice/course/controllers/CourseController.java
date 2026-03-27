package com.microservice.course.controllers;

import com.microservice.course.dto.CourseDTO;
import com.microservice.course.dto.EnrollmentDTO;
import com.microservice.course.entities.Course;
import com.microservice.course.entities.Enrollment;
import com.microservice.course.services.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCourse(@RequestBody Course course){
        courseService.save(course);
    }

    @GetMapping()
    public ResponseEntity<?> findAllCourses(){
        return ResponseEntity.ok(courseService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return ResponseEntity.ok(courseService.findById(id));
    }

    @GetMapping("/findStudentsByCourse/{id}")
    public ResponseEntity<?> findStudentsByCourseId(@PathVariable Long id){
        return ResponseEntity.ok(courseService.findStudentsByCourseId(id));
    }

    // Creates Student-Course Entity
    @PostMapping("/addStudentToCourse")
    public ResponseEntity<Enrollment> addStudentToCourse(@RequestBody EnrollmentDTO enrollmentDTO) {
        Enrollment enrollment = courseService.addStudentToCourse(enrollmentDTO.courseId(), enrollmentDTO.studentId());
        return ResponseEntity.status(HttpStatus.CREATED).body(enrollment);
    }
}
