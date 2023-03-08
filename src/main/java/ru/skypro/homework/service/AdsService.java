package ru.skypro.homework.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.skypro.homework.dto.*;

public interface AdsService {

    ResponseWrapper<AdsDto> getAllAds();
    ResponseWrapper<AdsDto> getAdsMe(Long userId);
    ResponseEntity<FullAdsDto> getFullAd(Long adId);
    ResponseEntity<AdsDto> addAds(Long userId, AdsDto adsDto);
    ResponseEntity<AdsCommentDto> getComments(int adPk, int id);
    ResponseEntity<HttpStatus> deleteComments(int adPk, int id);
    ResponseEntity<AdsCommentDto> updateComments(int adPk, int id, AdsCommentDto adsCommentDto);
    ResponseEntity<AdsDto> updateAds(Long userId);
    ResponseEntity<String> removeAds(Long userId);
    ResponseWrapper<AdsCommentDto> getAdsComments(long adPk);
    ResponseEntity<AdsCommentDto> addAdsComments(long adPk, AdsCommentDto adsCommentDto);
}
