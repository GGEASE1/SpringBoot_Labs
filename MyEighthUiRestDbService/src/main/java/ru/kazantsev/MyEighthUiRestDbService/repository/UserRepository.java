package ru.kazantsev.MyEighthUiRestDbService.repository;

import ru.kazantsev.MyEighthUiRestDbService.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>
{
    User findByEmail(String email);
}
