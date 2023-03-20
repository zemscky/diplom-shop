package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.RegisterReqDto;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.security.UserDetailsServiceImpl;
import ru.skypro.homework.service.AuthService;

import javax.validation.ValidationException;
import java.time.Instant;


@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final UserDetailsServiceImpl userDetailsServiceImpl;

    private final PasswordEncoder encoder;

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public boolean login(String userName, String password) {
        try {
            UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(userName);
            String encryptedPassword = userDetails.getPassword();
            return encoder.matches(password, encryptedPassword);
        } catch (UsernameNotFoundException e) {
            return false;
        }
    }

    @Override
    public boolean register(RegisterReqDto registerReqDto, Role role) {
        User user = userMapper.toEntity(registerReqDto);

        if (userRepository.existsByEmailIgnoreCase(user.getEmail())) {
            throw new ValidationException(String.format("Пользователь \"%s\" уже зарегистрирован!", user.getEmail()));
        }

        user.setPassword(encoder.encode(user.getPassword()));
        user.setRegDate(Instant.now());

        userRepository.save(user);

        return true;
    }
}
