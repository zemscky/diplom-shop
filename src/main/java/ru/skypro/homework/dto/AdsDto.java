package ru.skypro.homework.dto ;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
public class AdsDto {

    private int author;

    private String image;

    private long pk;

    private int price;

    @NotBlank
    private String title;

    @NotBlank
    @JsonIgnore
    private String description;

}
