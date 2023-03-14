package ru.skypro.homework.service;

import ru.skypro.homework.entity.User;

import java.util.Collection;


public interface UserService {
    /**
     * Finds a User instance in the repository, converts it to UserDto
     * and then return it with ResponseWrapper
     * @return ResponseWrapper<UserDto>
     */
    Collection<User> getUser();
    /**
     * Get user by ID
     * @param id ID user
     */
    User getUserById(long id);
}

