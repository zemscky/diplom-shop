package ru.skypro.homework.service;

import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.User;

import java.util.Collection;

public interface UserService {

    Collection<User> getUsers();

    /**
     * Changes user data
     *
     * @param userDto User object with new data
     * @return User
     */
    User updateUser(UserDto userDto);

}

