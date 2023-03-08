package ru.skypro.homework.dto;

import lombok.*;
import ru.skypro.homework.entity.Image;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class AdsDto {

    private int pk;

    @NotBlank
    @Size(min = 8)
    private String title;

    @NotBlank
    @Size(min = 8)
    private String description;

    private List<Image> imagesDto;

    private int price;

    private int author;

}
