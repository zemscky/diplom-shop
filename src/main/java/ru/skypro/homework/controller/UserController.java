package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.CreateUserDto;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.ResponseWrapper;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.service.UserService;
import ru.skypro.homework.entity.User;

import javax.validation.Valid;


@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Tag(name = "Пользователи", description = "UserController")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final UserMapper userMapper;

    @Operation(summary = "addUser", description = "addUser")
    @PostMapping
    public ResponseEntity<CreateUserDto> addUser(@Valid @RequestBody CreateUserDto createUserDto) {

        User user = userService.createUser(userMapper.createUserDtoToEntity(createUserDto));

        return ResponseEntity.ok(userMapper.toCreateUserDto(user));
    }

    @Operation(summary = "updateUser", description = "updateUser")
    @PatchMapping("/me")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        printLogInfo("updateUser", "patch", "/me");
        return ResponseEntity.ok(userMapper.toDto(userService.updateUser(userDto)));
    }

    @Operation(summary = "setPassword", description = "setPassword")
    @PostMapping("/set_password")
    public ResponseEntity<NewPasswordDto> setPassword(@RequestBody NewPasswordDto newPasswordDto) {
        userService.newPassword(newPasswordDto.getNewPassword(), newPasswordDto.getCurrentPassword());
        printLogInfo("setPassword", "post", "/set_password");
        return ResponseEntity.ok(newPasswordDto);
    }

    @Operation(summary = "updateUserImage", description = "updateUserImage")
    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MultipartFile> updateUserImage(@RequestBody MultipartFile image) {
        printLogInfo("updateUserImage", "patch", "/me/image");
        return ResponseEntity.ok(image);
    }

    @Operation(summary = "getUser", description = "getUser")
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") long id) {
        User user = userService.getUserById(id);
        printLogInfo("getUser", "get", "/id");
        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @Operation(summary = "getUsers", description = "getUsers")
    @GetMapping("/me")
    public UserDto getUsers() {
        printLogInfo("getUsers", "get", "/me");
        return userMapper.toDto((userService.getUsers()));
//        return ResponseWrapper.of(userMapper.toDto((userService.getUsers().));
    }

    private void printLogInfo(String name, String requestMethod, String path) {
        logger.info("Вызван метод " + name + ", адрес "
                + requestMethod.toUpperCase() + " запроса /users" + path);
    }
}
