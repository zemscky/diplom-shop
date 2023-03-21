package ru.skypro.homework.service;

import ru.skypro.homework.dto.RegisterReqDto;
import ru.skypro.homework.dto.Role;

/**
 * Service for user registration and login
 */
public interface AuthService {

    /**
     * @param userName Login (email)
     * @param password Password
     */
    boolean login(String userName, String password);

    /**
     * @param role User role
     */
    boolean register(RegisterReqDto registerReqDto, Role role);
}
