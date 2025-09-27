package ru.kazantsev.myeighthuirestdbservice.repository;

import ru.kazantsev.myeighthuirestdbservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>
{
    User findByEmail(String email);
}
