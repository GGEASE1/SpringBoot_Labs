package ru.kazantsev.myeighthuirestdbservice.repository;

import ru.kazantsev.myeighthuirestdbservice.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer>
{
}
