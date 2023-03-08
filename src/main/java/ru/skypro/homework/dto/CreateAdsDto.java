package ru.skypro.homework.dto;

import lombok.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Getter
@Setter
public class CreateAdsDto {
    private String description;
    private String image;
    private int pk;
    private int price;
    private String title;
}
