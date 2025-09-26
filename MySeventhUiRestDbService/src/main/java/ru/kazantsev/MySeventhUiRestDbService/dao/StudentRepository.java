package ru.kazantsev.MySeventhUiRestDbService.dao;

import ru.kazantsev.MySeventhUiRestDbService.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>
{

}
