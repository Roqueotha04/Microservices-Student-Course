package com.microservice.course.services;

import com.microservice.course.entities.Course;
import com.microservice.course.repositories.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService{

    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course findById(Long id) {
        return courseRepository.findById(id).orElseThrow(()-> new RuntimeException("Resource not found"));
    }

    @Override
    public void save(Course course) {
        courseRepository.save(course);
    }
}
