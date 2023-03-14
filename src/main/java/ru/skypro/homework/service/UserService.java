package ru.skypro.homework.service;

import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.User;
import java.util.Collection;

public interface UserService {
    /**
     * Create User
     *
     * @param user User object
     * @return User Created User
     */
    User createUser(User user);
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

    /**
     * Changes user data
     *
     * @param userDto User object with new data
     * @return User
     */
    User updateUser(UserDto userDto);

    /**
     * Изменение пароля пользователя
     *
     * @param newPassword     новый пароль
     * @param currentPassword старый пароль
     */
    void newPassword(String newPassword, String currentPassword);
}

