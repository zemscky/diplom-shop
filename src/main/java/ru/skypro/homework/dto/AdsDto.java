package ru.skypro.homework.dto ;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
public class AdsDto {

    private int pk;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    private byte[] image;

    private int price;

    private int author;

}
