package ru.kazantsev.myeighthuirestdbservice.repository;

import ru.kazantsev.myeighthuirestdbservice.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer>
{
    Role findByName(String name);
}
