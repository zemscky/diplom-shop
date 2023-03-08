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
public class CreateAdsDto {
    private String description;
    private List<Image> imagesDto;
    private int pk;
    private int price;
    private String title;
}
