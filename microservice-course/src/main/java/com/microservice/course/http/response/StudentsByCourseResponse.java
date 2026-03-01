package com.microservice.course.http.response;

import com.microservice.course.dto.StudentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

public record StudentsByCourseResponse (String courseName, String teacher, List<StudentDTO> studentsList){

}
