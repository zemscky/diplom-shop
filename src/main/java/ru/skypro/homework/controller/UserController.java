package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.service.UserService;

import java.util.List;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "Пользователи", description = "UserController")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @Operation(summary = "updateUser", description = "updateUser")
    @PatchMapping("/me")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userMapper.toDto(userService.updateUser(userDto)));
    }

    @Operation(summary = "setPassword", description = "setPassword")
    @PostMapping("/set_password")
    public ResponseEntity<NewPasswordDto> setPassword(@RequestBody NewPasswordDto newPasswordDto) {
        return ResponseEntity.ok(newPasswordDto);
    }

    @Operation(summary = "updateUserImage", description = "updateUserImage")
    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MultipartFile> updateUserImage(@RequestBody MultipartFile image) {
        return ResponseEntity.ok(image);
    }

    @Operation(summary = "getUsers", description = "getUsers")
    @GetMapping("/me")
    public ResponseEntity<List<UserDto>> getUsers() {
//        Collection<User> users = userService.getUsers();
//        временно так.
        return ResponseEntity.ok(List.of(new UserDto()));
    }
}
