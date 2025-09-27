package ru.kazantsev.MyEighthUiRestDbService.dao;

import ru.kazantsev.MyEighthUiRestDbService.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>
{

}
