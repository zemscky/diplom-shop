package ru.skypro.homework.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.service.UserService;

import java.util.Collection;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {
    private final AuthService authService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(AuthService authService, UserRepository userRepository, UserMapper userMapper) {
        this.authService = authService;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public Collection<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Пользователь с id " + id + " не найден!"));
    }

    @Override
    public User updateUser(UserDto userDto) {

        User user = new User(); //позже найти юзера, изменяющего данные о себе, если не нашили, то NotFound

        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhone(userDto.getPhone());

        return userRepository.save(user);
    }

    @Override
    public NewPasswordDto setPassword(NewPasswordDto newPasswordDto, Authentication authentication) {
        NewPasswordDto resultPassword = new NewPasswordDto();
        Optional<String> pass = authService.changePassword(
                        authentication.getName(),
                        newPasswordDto.getCurrentPassword(),
                        newPasswordDto.getNewPassword()
                );
        if (pass.isPresent()) {
            resultPassword.setCurrentPassword(pass.get());
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        return resultPassword;
    }
}
