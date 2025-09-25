package ru.kazantsev.MySixthSpringBoot2Dbase.dao;

import ru.kazantsev.MySixthSpringBoot2Dbase.entity.EducationalDesciplines;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Slf4j
@Repository
public class EducationalDesciplinesDAOImpl implements EducationalDesciplinesDAO
{

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<EducationalDesciplines> getAllEducationalDesciplines()
    {
        Query query = entityManager.createQuery("from EducationalDesciplines");
        List<EducationalDesciplines> educationalDesciplines = query.getResultList();
        log.info("all EducationalDesciplines" +educationalDesciplines);
        return educationalDesciplines;
    }

    @Override
    public EducationalDesciplines getEducationalDesciplineById(int id)
    {
        return entityManager.find(EducationalDesciplines.class, id);

    }

    @Override
    public EducationalDesciplines saveEducationalDesciplines(EducationalDesciplines educationalDesciplines)
    {
        return entityManager.merge(educationalDesciplines);
    }

    @Override
    public void deleteEducationalDesciplines(int id)
    {
        Query query = entityManager.createQuery("delete from EducationalDesciplines where id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
