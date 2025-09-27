package ru.kazantsev.MyEighthUiRestDbService.service;

import ru.kazantsev.MyEighthUiRestDbService.dto.UserDto;
import ru.kazantsev.MyEighthUiRestDbService.entity.User;

import java.util.List;

public interface UserService
{

    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}
