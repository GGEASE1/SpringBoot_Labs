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
        log.info("Вывод успешен");
        return educationalDesciplinesDAO.getAllEducationalDesciplines();
    }

    @Override
    @Transactional
    public ResponseEntity<EducationalDesciplines> getEducationalDescipline(int id)
    {
        EducationalDesciplines educationalDesciplines = educationalDesciplinesDAO.getEducationalDesciplineById(id);
        if (educationalDesciplines == null)
        {
            log.info("Информация не получена");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.info("Информация получена");
        return new ResponseEntity<>(educationalDesciplines,HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<EducationalDesciplines> saveEducationalDescipline(EducationalDesciplines educationalDesciplines)
    {
        EducationalDesciplines savedEducationalEdsciplines = educationalDesciplinesDAO.saveEducationalDesciplines(educationalDesciplines);
        if (savedEducationalEdsciplines == null)
        {
            log.info("Сохранение неудачно");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.info("Сохранение успешно");
        return new ResponseEntity<>(savedEducationalEdsciplines,HttpStatus.CREATED);
    }

    @Override
    @Transactional
    public ResponseEntity<EducationalDesciplines> deleteEducationalDescipline(int id)
    {
        EducationalDesciplines deletedEducationalDesciplines = educationalDesciplinesDAO.getEducationalDesciplineById(id);
        if (deletedEducationalDesciplines == null)
        {
            log.info("Удаление неудачно");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.info("Удаление успешно");
        educationalDesciplinesDAO.deleteEducationalDesciplines(id);
        return new ResponseEntity<>(deletedEducationalDesciplines,HttpStatus.OK);
    }
}
