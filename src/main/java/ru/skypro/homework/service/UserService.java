package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UserDto;
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

    /**
     * Changes user data
     *
     * @param userDto User object with new data
     * @return User
     */
    User updateUser(UserDto userDto);

    NewPasswordDto setPassword(NewPasswordDto newPasswordDto, Authentication authentication);
}

