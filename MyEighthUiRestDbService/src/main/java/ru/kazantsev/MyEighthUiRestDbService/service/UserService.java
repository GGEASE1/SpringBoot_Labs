package ru.kazantsev.myeighthuirestdbservice.service;

import ru.kazantsev.myeighthuirestdbservice.dto.UserDto;
import ru.kazantsev.myeighthuirestdbservice.entity.User;

import java.util.List;

public interface UserService
{

    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}
