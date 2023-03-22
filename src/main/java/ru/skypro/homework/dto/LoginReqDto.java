package ru.skypro.homework.dto;

import lombok.*;

@Data
@AllArgsConstructor
public class LoginReqDto {
    private String username;
    private String password;
}
