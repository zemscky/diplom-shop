package ru.skypro.homework.dto;

import lombok.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Getter
@Setter
public class LoginReqDto {
    private String password;
    private String username;

}
