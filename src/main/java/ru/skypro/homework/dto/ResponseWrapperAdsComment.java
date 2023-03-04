package ru.skypro.homework.dto;

import lombok.Data;

import java.util.Collection;

@Data
public class ResponseWrapperAdsComment {
    private int count;
    private Collection<AdsCommentDto> results;
}
