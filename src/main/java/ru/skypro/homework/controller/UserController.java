package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.service.UserService;

import java.util.Collection;
import java.util.List;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
//    private final UserService userService;

    @PatchMapping("/me")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(new UserDto());
    }

    @PostMapping("/set_password")
    public ResponseEntity<NewPasswordDto> setPassword(@RequestBody NewPasswordDto newPasswordDto) {
        return ResponseEntity.ok(newPasswordDto);
    }

    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MultipartFile> updateUserImage(@RequestParam MultipartFile image) {
        return ResponseEntity.ok(image);
    }

    @GetMapping("/me")
    public ResponseEntity<List<UserDto>> getUsers() {
//        Collection<User> users = userService.getUsers();
//временно так
        return ResponseEntity.ok(List.of(new UserDto()));
    }
}
