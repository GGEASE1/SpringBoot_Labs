package ru.kazantsev.MySixthSpringBoot2Dbase.service;

import ru.kazantsev.MySixthSpringBoot2Dbase.entity.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService
{

    List<Student> getAllStudents();

    ResponseEntity<Student> saveStudent(Student student);

    ResponseEntity<Student>  getStudent(int id);

    ResponseEntity<Student> deleteStudent(int id);
}
