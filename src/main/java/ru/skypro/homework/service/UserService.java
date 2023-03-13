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
     * Update user
     * @return ResponseWrapper<UserDto>
     */
    User updateUser(User updatedUser);
    User getUserById(long id);

}

