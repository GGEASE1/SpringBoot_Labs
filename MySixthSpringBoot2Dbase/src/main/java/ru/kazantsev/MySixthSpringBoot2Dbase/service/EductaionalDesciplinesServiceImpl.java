package ru.kazantsev.MySixthSpringBoot2Dbase.service;

import ru.kazantsev.MySixthSpringBoot2Dbase.dao.EducationalDesciplinesDAO;
import ru.kazantsev.MySixthSpringBoot2Dbase.entity.EducationalDesciplines;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class EductaionalDesciplinesServiceImpl implements EducationalDesciplinesService
{

    @Autowired
    private EducationalDesciplinesDAO educationalDesciplinesDAO;

    @Override
    @Transactional
    public List<EducationalDesciplines> getAllEducationalDesciplines()
    {
        return educationalDesciplinesDAO.getAllEducationalDesciplines();
    }

    @Override
    @Transactional
    public ResponseEntity<EducationalDesciplines> getEducationalDescipline(int id)
    {
        EducationalDesciplines educationalDesciplines = educationalDesciplinesDAO.getEducationalDesciplineById(id);
        if (educationalDesciplines == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(educationalDesciplines,HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<EducationalDesciplines> saveEducationalDescipline(EducationalDesciplines educationalDesciplines)
    {
        EducationalDesciplines savedEducationalEdsciplines = educationalDesciplinesDAO.saveEducationalDesciplines(educationalDesciplines);
        if (savedEducationalEdsciplines == null)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(savedEducationalEdsciplines,HttpStatus.CREATED);
    }

    @Override
    @Transactional
    public ResponseEntity<EducationalDesciplines> deleteEducationalDescipline(int id)
    {
        EducationalDesciplines deletedEducationalDesciplines = educationalDesciplinesDAO.getEducationalDesciplineById(id);
        if (deletedEducationalDesciplines == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        educationalDesciplinesDAO.deleteEducationalDesciplines(id);
        return new ResponseEntity<>(deletedEducationalDesciplines,HttpStatus.OK);
    }
}
