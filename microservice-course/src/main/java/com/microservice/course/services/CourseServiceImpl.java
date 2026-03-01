package com.microservice.course.services;

import com.microservice.course.client.StudentClient;
import com.microservice.course.dto.StudentDTO;
import com.microservice.course.entities.Course;
import com.microservice.course.http.response.StudentsByCourseResponse;
import com.microservice.course.repositories.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService{

    private final CourseRepository courseRepository;
    private final StudentClient studentClient;

    public CourseServiceImpl(CourseRepository courseRepository, StudentClient studentClient) {
        this.courseRepository = courseRepository;
        this.studentClient = studentClient;
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

    @Override
    public StudentsByCourseResponse findStudentsByCourseId(Long id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course not found"));

        List<StudentDTO> studentDTOList = studentClient.findAllStudentsByCourse(id);

        return new StudentsByCourseResponse(course.getName(), course.getTeacher(), studentDTOList);
    }
}
