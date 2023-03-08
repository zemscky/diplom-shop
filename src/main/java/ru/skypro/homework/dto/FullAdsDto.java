package ru.skypro.homework.dto;

import lombok.*;
import ru.skypro.homework.entity.Image;

import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Getter
@Setter
public class FullAdsDto {
    private List<Image> imagesDto;
    private String authorLastName;
    private String authorFirstName;
    private String phone;
    private int price;
    private String description;
    private int pk;
    private String title;
    private String email;
}
