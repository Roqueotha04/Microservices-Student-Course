package com.microservice.student;

import com.microservice.student.entities.Student;

import java.util.List;

public class DataProvider {

    public static List<Student> studentListMock(){
        return List.of(
                new Student("spiderman@gmail.com", "Peter", "Parker"),
                new Student("liomessi@gmail.com", "lionel", "Messi"),
                new Student("batman@gmail.com", "Bruce", "Wayne")
        );
    }
}
