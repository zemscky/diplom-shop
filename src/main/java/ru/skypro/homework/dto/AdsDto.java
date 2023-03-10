package ru.skypro.homework.dto ;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class AdsDto {

    private int pk;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    private List<String> imagesDto;

    private int price;

    private int author;

}
