package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.RegisterReqDto;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;

import javax.validation.ValidationException;

@Transactional
@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final UserDetailsManager manager;

    private final PasswordEncoder encoder;

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public boolean login(String userName, String password) {
//        if (!userRepository.existsByEmail(userName)) {
//            throw new ValidationException(String.format("Пользователь \"%s\" не существует!", userName));
//        }

//        UserDetails user = manager.loadUserByUsername(userName);
        User user = userRepository.findByEmail(userName).orElseThrow(() -> {
            System.out.println("ValidationException(String.format(Пользователь не существует!");
            return new ValidationException(String.format("Пользователь \"%s\" не существует!", userName));
        });

        if (!encoder.matches(password, user.getPassword())) {
            System.out.println("passwordEncoder.matches  Неверно указан пароль!");
            throw new BadCredentialsException("Неверно указан пароль!");
        }

        return true;
//        String encryptedPassword = user.getPassword();
//        String encryptedPasswordWithoutEncryptionType = encryptedPassword.substring(8);
//        return encoder.matches(password, encryptedPasswordWithoutEncryptionType);
    }

    @Override
    public boolean register(RegisterReqDto registerReqDto, Role role) {
        User user = userMapper.toEntity(registerReqDto);

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new ValidationException(String.format("Пользователь \"%s\" уже зарегистрирован!", user.getEmail()));
        }

        user.setPassword(encoder.encode(user.getPassword()));

        userRepository.save(user);

        return true;
    }
}
