package ru.kazantsev.MySixthSpringBoot2Dbase.service;

import ru.kazantsev.MySixthSpringBoot2Dbase.dao.StudentDAO;
import ru.kazantsev.MySixthSpringBoot2Dbase.entity.Student;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService
{

    @Autowired
    private StudentDAO studentDAO;

    @Override
    @Transactional
    public List<Student> getAllStudents()
    {
        return studentDAO.getAllStudents();
    }

    @Override
    @Transactional
    public ResponseEntity<Student>  saveStudent(Student student)
    {
        Student savedStudent = studentDAO.saveStudent(student);
        if(savedStudent == null)
        {
            return new ResponseEntity<>(student, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    @Override
    @Transactional
    public ResponseEntity<Student> getStudent(int id)
    {

        Student student = studentDAO.getStudent(id);
        if(student == null)
        {
            return new ResponseEntity<>(student, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<Student> deleteStudent(int id)
    {

        Student student = studentDAO.getStudent(id);
        if(student == null)
        {
            return new ResponseEntity<>(student, HttpStatus.NOT_FOUND);
        }
        studentDAO.deleteStudent(id);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }
}
