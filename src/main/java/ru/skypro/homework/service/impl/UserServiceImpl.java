package ru.skypro.homework.service.impl;

import liquibase.repackaged.net.sf.jsqlparser.util.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.security.UserDetailsServiceImpl;
import ru.skypro.homework.service.UserService;

import java.util.Collection;

import static ru.skypro.homework.security.SecurityUtils.getUserDetailsFromContext;

import static ru.skypro.homework.dto.Role.USER;

@Transactional
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private UserDetailsServiceImpl userDetailsService;

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

    public User createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new ValidationException(String.format("Пользователь \"%s\" уже существует!", user.getEmail()));
        }

        if (user.getRole() == null) {
            user.setRole(USER);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public User updateUser(UserDto userDto) {
        User user = getUserById(getUserDetailsFromContext().getId());

        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhone(userDto.getPhone());

        return userRepository.save(user);
    }

    @Override
    public void newPassword(String newPassword, String currentPassword) {

        UserDetails userDetails = getUserDetailsFromContext();

        if (!passwordEncoder.matches(currentPassword, userDetails.getPassword())) {
            throw new BadCredentialsException("Неверно указан текущий пароль!");
        }

        userDetailsService.updatePassword(userDetails, passwordEncoder.encode(newPassword));
    }


}
