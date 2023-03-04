package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class AdsDto {
    private int pk;
    private String title;
    private String description;
    private String image;
    private int price;
    private int author;
}
