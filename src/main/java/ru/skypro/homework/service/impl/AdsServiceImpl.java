package ru.skypro.homework.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.service.AdsService;

@Service
public class AdsServiceImpl implements AdsService {

    @Override
    public ResponseWrapperAds getAllAds() {
        return null;
    }

    @Override
    public ResponseWrapperAds getAdsMe(Long userId) {
        return null;
    }

    @Override
    public ResponseEntity<FullAdsDto> getFullAd(Long adId) {
        return null;
    }

    @Override
    public ResponseEntity<AdsDto> addAds(Long userId, AdsDto adsDto) {
        return null;
    }

    @Override
    public ResponseEntity<AdsCommentDto> getComments(int adPk, int id) {
        return null;
    }

    @Override
    public ResponseEntity<HttpStatus> deleteComments(int adPk, int id) {
        return null;
    }

    @Override
    public ResponseEntity<AdsCommentDto> updateComments(int adPk, int id, AdsCommentDto adsCommentDto) {
        return null;
    }

    @Override
    public ResponseEntity<AdsDto> updateAds(Long userId) {
        return null;
    }

    @Override
    public ResponseEntity<String> removeAds(Long userId) {
        return null;
    }

    @Override
    public ResponseWrapperAdsComment<AdsCommentDto> getAdsComments(long adPk) {
        return null;
    }

    @Override
    public ResponseEntity<AdsCommentDto> addAdsComments(long adPk, AdsCommentDto adsCommentDto) {
        return null;
    }
}
