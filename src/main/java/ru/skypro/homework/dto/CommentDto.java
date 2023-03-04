package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class CommentDto {
    private int pk;
    private int author;
    private String createdAt;
    private String text;
}
