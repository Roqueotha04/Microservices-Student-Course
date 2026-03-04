package com.microservice.student.services;

import com.microservice.student.DataProvider;
import com.microservice.student.entities.Student;
import com.microservice.student.repositories.StudentRespository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class StudentServiceImplTest {

    @Mock
    private StudentRespository studentRespository;

    @InjectMocks
    private StudentServiceImpl studentService;


    @Test
    public void testFindAll(){
        List<Student> studentListMock = DataProvider.studentListMock();

        when(studentRespository.findAll()).thenReturn(studentListMock);

        List<Student> result = studentService.findAll();

        assertFalse(result.isEmpty());
        assertEquals(studentListMock.get(0).getEmail(), result.get(0).getEmail());
        verify(this.studentRespository).findAll();
    }
}
