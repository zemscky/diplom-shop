package ru.skypro.homework.dto;

import lombok.*;

import java.util.List;

@Data
public class FullAdsDto {

    private byte[] image;
    private String authorLastName;
    private String authorFirstName;
    private String phone;
    private int price;
    private String description;
    private int pk;
    private String title;
    private String email;
}
