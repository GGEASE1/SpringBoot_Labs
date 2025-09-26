package ru.kazantsev.MySeventhUiRestDbService.controller;

import ru.kazantsev.MySeventhUiRestDbService.dao.StudentRepository;
import ru.kazantsev.MySeventhUiRestDbService.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@Slf4j
@Controller
public class StudentController
{

    private final StudentRepository studentRepository;

    @Autowired
    public StudentController(StudentRepository studentRepository) {this.studentRepository = studentRepository;}

    @GetMapping({"/list", "/"})
    public ModelAndView getAllStudents()
    {
        ModelAndView mav = new ModelAndView("list.students");
        mav.addObject("students", studentRepository.findAll());
        return mav;
    }

    @GetMapping("/addStudentForm")
    public ModelAndView addStudentForm()
    {
        ModelAndView mav = new ModelAndView("add-student-form");
        mav.addObject("student", new Student());
        return mav;
    }

    @PostMapping("/saveStudent")
    public RedirectView saveStudent(@ModelAttribute Student student)
    {
        studentRepository.save(student);
        return new RedirectView("list");
    }

    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateFora(@RequestParam int studentId)
    {
        ModelAndView mav = new ModelAndView("add-student-form");
        Optional<Student> optioanlStudent = studentRepository.findById(studentId);
        Student student = new Student();
        if(optioanlStudent.isPresent())
        {
            student = optioanlStudent.get();
        }
        mav.addObject("student", student);
        return mav;
    }

    @GetMapping("/deleteStudent")
    public RedirectView deleteStudent(@RequestParam int studentId, ModelAndView model)
    {
        studentRepository.deleteById(studentId);
        return new RedirectView("list");
    }
}
