package ru.skypro.homework.dto;

import lombok.Data;

import java.util.List;

@Data
public class FullAdsDto {
    private List<String> imagesDto;
    private String authorLastName;
    private String authorFirstName;
    private String phone;
    private int price;
    private String description;
    private int pk;
    private String title;
    private String email;
}
