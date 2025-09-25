package ru.kazantsev.MySixthSpringBoot2Dbase.controller;

import ru.kazantsev.MySixthSpringBoot2Dbase.entity.EducationalDesciplines;
import ru.kazantsev.MySixthSpringBoot2Dbase.service.EducationalDesciplinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DesciplinesController
{

    @Autowired
    private EducationalDesciplinesService educationalDesciplinesService;

    @GetMapping("/desciplines")
    public List<EducationalDesciplines> getAllDesciplines()
    {
        return educationalDesciplinesService.getAllEducationalDesciplines();
    }

    @GetMapping("/desciplines/{id}")
    public ResponseEntity<EducationalDesciplines> getDesciplineById(@PathVariable("id") int id)
    {
        return educationalDesciplinesService.getEducationalDescipline(id);
    }

    @PostMapping("/descipline")
    public ResponseEntity<EducationalDesciplines> createDescipline(@RequestBody EducationalDesciplines educationalDesciplines)
    {
        return  educationalDesciplinesService.saveEducationalDescipline(educationalDesciplines);
    }

    @PutMapping("/descipline")
    public ResponseEntity<EducationalDesciplines> saveDescipline(@RequestBody EducationalDesciplines educationalDesciplines)
    {
        return educationalDesciplinesService.saveEducationalDescipline(educationalDesciplines);
    }

    @DeleteMapping("descipline/{id}")
    public ResponseEntity<EducationalDesciplines> deleteDescipline(@PathVariable("id") int id)
    {
        return educationalDesciplinesService.deleteEducationalDescipline(id);
    }
}
