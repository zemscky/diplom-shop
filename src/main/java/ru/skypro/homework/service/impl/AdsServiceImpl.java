package ru.skypro.homework.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.mapper.AdsMapper;
import ru.skypro.homework.repository.AdsCommentRepository;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.service.AdsService;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class AdsServiceImpl implements AdsService {

    private final AdsRepository adsRepository;
    private final AdsCommentRepository adsCommentRepository;
    private final AdsMapper adsMapper;

    public AdsServiceImpl(AdsRepository adsRepository,
                          AdsCommentRepository adsCommentRepository,
                          AdsMapper adsMapper) {
        this.adsRepository = adsRepository;
        this.adsCommentRepository = adsCommentRepository;
        this.adsMapper = adsMapper;
    }

    @Override
    public ResponseWrapper<AdsDto> getAllAds() {
        Collection<AdsDto> allAdsDto = adsMapper.toDto(adsRepository.findAll());
        return ResponseWrapper.of(allAdsDto);
    }

    @Override
    public ResponseWrapper<AdsDto> getAdsMe(Long userId) {
        return ResponseWrapper.of(new ArrayList<AdsDto>());
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
    public ResponseWrapper<AdsCommentDto> getAdsComments(long adPk) {
        return ResponseWrapper.of(new ArrayList<AdsCommentDto>());
    }

    @Override
    public ResponseEntity<AdsCommentDto> addAdsComments(long adPk, AdsCommentDto adsCommentDto) {
        return null;
    }
}
