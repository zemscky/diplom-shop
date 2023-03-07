package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static ru.skypro.homework.constant.Regexp.EMAIL_REGEXP;
import static ru.skypro.homework.constant.Regexp.PHONE_REGEXP;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Getter
@Setter
public class UserDto {

    private int id;

    @NotBlank
    @Size(min = 3)
    private String firstName;

    @NotBlank
    @Size(min = 3)
    private String lastName;

    @Pattern(regexp = PHONE_REGEXP)
    private String phone;

    @Email(regexp = EMAIL_REGEXP)
    @Schema(example = "user@user.ru")
    private String email;

    private String regDate;

    private String city;

    private String image;

}
