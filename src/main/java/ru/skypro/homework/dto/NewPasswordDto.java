package ru.skypro.homework.dto;

import lombok.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Getter
@Setter
public class NewPasswordDto {
    private String currentPassword;
    private String newPassword;
}
