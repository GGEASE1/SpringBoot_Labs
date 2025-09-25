package ru.kazantsev.MySixthSpringBoot2Dbase.dao;

import ru.kazantsev.MySixthSpringBoot2Dbase.entity.EducationalDesciplines;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EducationalDesciplinesDAO
{

    List<EducationalDesciplines> getAllEducationalDesciplines();

    EducationalDesciplines getEducationalDesciplineById(int id);

    EducationalDesciplines saveEducationalDesciplines(EducationalDesciplines educationalDesciplines);

    void deleteEducationalDesciplines(int id);
}
