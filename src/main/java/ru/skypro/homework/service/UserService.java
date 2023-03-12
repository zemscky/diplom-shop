package ru.skypro.homework.service;

import ru.skypro.homework.dto.ResponseWrapper;
import ru.skypro.homework.dto.UserDto;


public interface UserService {
    /**
     * Finds a User instance in the repository, converts it to UserDto
     * and then return it with ResponseWrapper
     * @return ResponseWrapper<UserDto>
     */
    ResponseWrapper<UserDto> getUser();

}

