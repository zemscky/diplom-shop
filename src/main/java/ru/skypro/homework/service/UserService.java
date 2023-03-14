package ru.skypro.homework.service;

import ru.skypro.homework.entity.User;

import java.util.Collection;


public interface UserService {
    /**
     * Get all users from repository
     * and return them all
     * @return Collection<User>
     */
    Collection<User> getUsers();
    /**
     * Get user by ID
     * @param id ID user
     */
    User getUserById(long id);
}

