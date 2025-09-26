package ru.kazantsev.MySixthSpringBoot2Dbase.controller;

import ru.kazantsev.MySixthSpringBoot2Dbase.entity.Student;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kazantsev.MySixthSpringBoot2Dbase.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyController
{

    @Autowired
    private StudentService studentService;

    @GetMapping(value ="/students")
    public List<Student> allStudents()
    {
        List<Student> allStudents = studentService.getAllStudents();
        return allStudents;
    }

    @GetMapping(value ="/students/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable("id") int id)
    {

        return studentService.getStudent(id);

    }

    @PostMapping(value ="/students")
    public ResponseEntity<Student> saveStudent(@RequestBody Student student)
    {
        return studentService.saveStudent(student);
    }

    @PutMapping(value ="/students")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student)
    {

        return studentService.saveStudent(student);
    }

    @DeleteMapping(value ="/students/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable("id") int id)
    {
        return studentService.deleteStudent(id);
    }
}
