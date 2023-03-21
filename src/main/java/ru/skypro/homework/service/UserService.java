package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.User;

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
     *
     * @return Collection<User>
     */

    User getUser();

    /**
     * Get user by ID
     *
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
     * Changing the user's password
     *
     * @param newPassword     New Password
     * @param currentPassword Current Password
     */
    void newPassword(String newPassword, String currentPassword);

    /**
     * Updates users image
     *
     * @param image new image
     * @return
     */
    String updateUserImage(MultipartFile image);

    /**
     * Changing a user's role
     *
     * @param id   identifier User
     * @param role New Role
     */
    User updateRole(long id, Role role);
}

