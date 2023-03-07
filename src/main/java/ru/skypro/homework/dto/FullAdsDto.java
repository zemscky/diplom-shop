package ru.skypro.homework.dto;

import lombok.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Getter
@Setter
public class FullAdsDto {
    private String image;
    private String authorLastName;
    private String authorFirstName;
    private String phone;
    private int price;
    private String description;
    private int pk;
    private String title;
    private String email;
}
