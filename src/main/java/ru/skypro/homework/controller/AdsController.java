package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.AdsCommentDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.FullAdsDto;
import ru.skypro.homework.dto.ResponseWrapper;
import ru.skypro.homework.service.AdsService;

import java.util.ArrayList;

@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
@RestController
@RequestMapping("/ads")
public class AdsController {

    private final AdsService adsService;

    @Operation(summary = "getAllAds", description = "getAllAds")
    @GetMapping
    public ResponseWrapper<AdsDto> getAllAds() {
        return adsService.getAllAds();
    }

    @Operation(summary = "getAdsMe", description = "getAdsMe")
    @GetMapping("/me")
    public ResponseWrapper<AdsDto> getAdsMe() {
        return adsService.getAdsMe();
    }

    @Operation(summary = "getFullAd", description = "getFullAd")
    @GetMapping("/{adId}")
    public ResponseEntity<FullAdsDto> getFullAd(@PathVariable("adId") Long adId) {
        return adsService.getFullAd(adId);
    }

    @Operation(summary = "addAds", description = "addAds")
    @PostMapping("/{userId}")
    public ResponseEntity<AdsDto> addAds(@PathVariable("userId") Long userId,
                                         @RequestBody AdsDto adsDto) {
        return ResponseEntity.ok(new AdsDto());
    }

    @Operation(summary = "getComments", description = "getComments")
    @GetMapping("/{ad_pk}/comments/{id}")
    public ResponseEntity<AdsCommentDto> getComments(@PathVariable("ad_pk") int adPk,
                                                     @PathVariable("id") int id) {
        return ResponseEntity.ok(new AdsCommentDto());
    }

    @Operation(summary = "deleteComments", description = "deleteComments")
    @DeleteMapping("/{ad_pk}/comments/{id}")
    public ResponseEntity<HttpStatus> deleteComments(@PathVariable("ad_pk") int adPk,
                                                       @PathVariable("id") int id) {
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "updateComments", description = "updateComments")
    @PatchMapping("/{ad_pk}/comments/{id}")
    public ResponseEntity<AdsCommentDto> updateComments(@PathVariable("ad_pk") int adPk,
                                                     @PathVariable("id") int id,
                                                     @RequestBody AdsCommentDto adsCommentDto) {
        return ResponseEntity.ok(new AdsCommentDto());
    }

    @Operation(summary = "updateAds", description = "updateAds")
    @PatchMapping("/{userId}")
    public ResponseEntity<AdsDto> updateAds(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(new AdsDto());
    }

    @Operation(summary = "removeAds", description = "removeAds")
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> removeAds(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok("Объявление успешно удалено");
    }

    @Operation(summary = "getAdsComments", description = "getAdsComments")
    @GetMapping("/{ad_pk}/comments")
    public ResponseWrapper<AdsCommentDto> getAdsComments(@PathVariable("ad_pk") long adPk) {
        return ResponseWrapper.of(new ArrayList<AdsCommentDto>());
    }

    @Operation(summary = "addAdsComments", description = "addAdsComments")
    @PostMapping("/{ad_pk}/comments")
    public ResponseEntity<AdsCommentDto> addAdsComments(@PathVariable("ad_pk") long adPk,
                                                        @RequestBody AdsCommentDto adsCommentDto) {
        return ResponseEntity.ok(new AdsCommentDto());
    }
}
