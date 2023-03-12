package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.service.UserService;

import java.util.Collection;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public Collection<User> getUsers() {
        return null;
    }
}
