package ru.kazantsev.MyEighthUiRestDbService.repository;

import ru.kazantsev.MyEighthUiRestDbService.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer>
{
    Role findByName(String name);
}
