package ru.skypro.homework.dto ;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
public class AdsDto {

    private int author;

    private byte[] image;

    private int pk;

    private int price;

    @NotBlank
    private String title;

    @NotBlank
    @JsonIgnore
    private String description;

}
