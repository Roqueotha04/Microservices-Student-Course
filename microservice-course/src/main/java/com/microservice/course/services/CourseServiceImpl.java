package com.microservice.course.services;

import com.microservice.course.client.StudentClient;
import com.microservice.course.config.RabbitMQConfig;

import com.microservice.course.dto.StudentDTO;
import com.microservice.course.entities.Course;
import com.microservice.course.entities.Enrollment;
import com.microservice.course.exceptions.ResourceNotFoundException;
import com.microservice.course.http.request.NotificationRequest;
import com.microservice.course.http.response.StudentsByCourseResponse;
import com.microservice.course.repositories.CourseRepository;
import com.microservice.course.repositories.EnrollmenRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CourseServiceImpl implements CourseService{

    private final CourseRepository courseRepository;
    private final EnrollmenRepository enrollmenRepository;
    private final RabbitTemplate rabbitTemplate;
    private final StudentClient studentClient;

    public CourseServiceImpl(CourseRepository courseRepository, EnrollmenRepository enrollmenRepository, RabbitTemplate rabbitTemplate, StudentClient studentClient) {
        this.courseRepository = courseRepository;
        this.enrollmenRepository = enrollmenRepository;
        this.rabbitTemplate = rabbitTemplate;
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

        Course course = findById(courseId);
        StudentDTO student = validateAndGetStudent(studentId);

        Enrollment enrollment = new Enrollment(courseId, studentId);

        NotificationRequest notification = new NotificationRequest(student.email(), "thanks for enrolling", "temardovich");
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_NAME,
                RabbitMQConfig.ROUTING_KEY,
                notification
        );
        return enrollmenRepository.save(enrollment);
    }

    private StudentDTO validateAndGetStudent(Long studentId) {
        try {
            return studentClient.findStudentById(studentId);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Student with ID " + studentId + " does not exist");
        }
    }
}
