package ru.skypro.homework.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.AdsComment;

public interface AdsService {

    /**
     * Finds all Ads instances in the repository, converts them to AdsDto
     * and then returns them using the ResponseWrapper
     * @return ResponseWrapper<AdsDto>
     */
    ResponseWrapper<AdsDto> getAllAds();

    /**
     * Finds ads created by the user who makes this request.
     * @return ResponseWrapper<AdsDto>
     */
    ResponseWrapper<AdsDto> getAdsMe();

    /**
     * Finds an Ads instance in the repository by its id and converts it into
     * a FullAdsDto instance. The purpose is to provide the user with the
     * full information on the chosen advertisement.
     * @param adId Ad ID
     * @return ResponseEntity<FullAdsDto>
     */
    ResponseEntity<FullAdsDto> getFullAd(Long adId);
    ResponseEntity<AdsDto> addAds(Long userId, AdsDto adsDto);
    /**
     * Getting a comment by ID
     *
     * @param id    Comment ID
     * @param adPk  Ad ID
     * @return      Found comment
     */
    AdsComment getAdsComment(long adPk, long id);

    /**
     * Deletes a comment
     * @param   adPk    Ads id
     * @param   id      AdsComment id
     * @return  ResponseEntity<HttpStatus>
     */
    ResponseEntity<HttpStatus> deleteComments(long adPk, long id);
    /**
     * Changing the comment by ID
     *
     * @param id            Comment id
     * @param adPk          Ad id
     * @param adsComment    Modified comment
     * @return Modified comment
     */
    AdsComment updateComments(int adPk, int id, AdsComment adsComment);
    ResponseEntity<AdsDto> updateAds(Long userId);
    ResponseEntity<String> removeAds(Long userId);
    ResponseWrapper<AdsCommentDto> getAdsComments(long adPk);
    ResponseEntity<AdsCommentDto> addAdsComments(long adPk, AdsCommentDto adsCommentDto);
}
