package com.microservice.course.services;

import com.microservice.course.entities.Course;
import com.microservice.course.entities.Enrollment;
import com.microservice.course.http.response.StudentsByCourseResponse;

import java.util.List;

public interface CourseService {

    public List<Course> findAll();
    public Course findById(Long id);
    public void save (Course course);
    public StudentsByCourseResponse findStudentsByCourseId(Long id);
    public Enrollment addStudentToCourse(Long studentId, Long courseId);
}
