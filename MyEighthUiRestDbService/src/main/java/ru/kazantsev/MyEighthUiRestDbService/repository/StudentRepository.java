package ru.kazantsev.MyEighthUiRestDbService.repository;

import ru.kazantsev.MyEighthUiRestDbService.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer>
{
}
