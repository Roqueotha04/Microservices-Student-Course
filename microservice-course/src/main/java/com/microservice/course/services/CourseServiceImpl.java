package com.microservice.course.services;

import com.microservice.course.client.StudentClient;
import com.microservice.course.dto.StudentDTO;
import com.microservice.course.entities.Course;
import com.microservice.course.entities.Enrollment;
import com.microservice.course.exceptions.ResourceNotFoundException;
import com.microservice.course.http.response.StudentsByCourseResponse;
import com.microservice.course.repositories.CourseRepository;
import com.microservice.course.repositories.EnrollmenRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CourseServiceImpl implements CourseService{

    private final CourseRepository courseRepository;
    private final EnrollmenRepository enrollmenRepository;
    private final StudentClient studentClient;

    public CourseServiceImpl(CourseRepository courseRepository, EnrollmenRepository enrollmenRepository, StudentClient studentClient) {
        this.courseRepository = courseRepository;
        this.enrollmenRepository = enrollmenRepository;
        this.studentClient = studentClient;
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course findById(Long id) {
        return courseRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Could not found course with id: " +id));
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

    //Later I´ll add validations to not add Students that are already enrolled in the course
    @Override
    public Enrollment addStudentToCourse(Long courseId, Long studentId) {
        log.info("Starting enrollment: Student ID {} into Course ID {}", studentId, courseId);

        findById(courseId);
        try {
            studentClient.findStudentById(studentId);
        } catch (Exception e) {
            log.error("Validation failed for Student ID {}: {}", studentId, e.getMessage());
            throw new ResourceNotFoundException("Student with ID " + studentId + " does not exist in external service");
        }

        Enrollment enrollment = new Enrollment(courseId, studentId);
        return enrollmenRepository.save(enrollment);
    }
}
