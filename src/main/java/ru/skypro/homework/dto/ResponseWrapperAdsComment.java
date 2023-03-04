package ru.skypro.homework.dto;

import lombok.Data;

import java.util.Collection;
import java.util.List;

@Data
public class ResponseWrapperAdsComment<T> {
    private int count;
    private Collection<AdsCommentDto> results;
    public static <T> ResponseWrapperAdsComment<T> of(List<T> results) {
        ResponseWrapperAdsComment<T> responseWrapperAdsComment = new ResponseWrapperAdsComment<>();
        return responseWrapperAdsComment;
    }
}
