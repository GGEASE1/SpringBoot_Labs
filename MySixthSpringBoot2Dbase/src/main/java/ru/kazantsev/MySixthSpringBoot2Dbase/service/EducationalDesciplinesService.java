package ru.kazantsev.MySixthSpringBoot2Dbase.service;

import ru.kazantsev.MySixthSpringBoot2Dbase.entity.EducationalDesciplines;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EducationalDesciplinesService
{

    List<EducationalDesciplines> getAllEducationalDesciplines();

    ResponseEntity<EducationalDesciplines> getEducationalDescipline(int id);

    ResponseEntity<EducationalDesciplines>  saveEducationalDescipline(EducationalDesciplines educationalDesciplines);

    ResponseEntity<EducationalDesciplines>  deleteEducationalDescipline(int id);
}
