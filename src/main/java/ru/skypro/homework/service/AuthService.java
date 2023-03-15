package ru.skypro.homework.service;

import ru.skypro.homework.dto.RegisterReqDto;
import ru.skypro.homework.dto.Role;

/**
 * Сервис для регистрации пользователя и входа
 */
public interface AuthService {

    /**
     * @param userName Логин (email)
     * @param password Пароль
     */
    boolean login(String userName, String password);

    /**
     * @param role Роль пользователя
     */
    boolean register(RegisterReqDto registerReqDto, Role role);
}
