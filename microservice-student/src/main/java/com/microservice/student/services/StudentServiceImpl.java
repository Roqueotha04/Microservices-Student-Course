package com.microservice.student.services;

import com.microservice.student.entities.Student;
import com.microservice.student.repositories.StudentRespository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{

    private final StudentRespository studentRespository;

    public StudentServiceImpl(StudentRespository studentRespository) {
        this.studentRespository = studentRespository;
    }

    @Override
    public List<Student> findAll() {
        return studentRespository.findAll().stream().toList();
    }

    @Override
    public Student findById(Long id) {
        return studentRespository.findById(id).orElseThrow(()-> new RuntimeException("Resource not found"));
    }

    @Override
    public void save(Student student) {
        studentRespository.save(student);
    }

    @Override
    public List<Student> findByCourseId(Long courseId) {
        return studentRespository.findAllByCourseId(courseId);
    }
}
