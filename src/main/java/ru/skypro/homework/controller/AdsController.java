package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.AdsCommentDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.ResponseWrapperAdsComment;
import ru.skypro.homework.service.AdsService;

import java.util.ArrayList;
@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
@RestController
@RequestMapping("/ads")
public class AdsController {

    private final AdsService adsService;

    @PostMapping("/{id}/new-ad")
    public ResponseEntity<AdsDto> addAds(@PathVariable("id") Long userId,
                                         @RequestBody AdsDto adsDto) {
        return ResponseEntity.ok(new AdsDto());
    }
    @GetMapping("/{ad_pk}/comments")
    public ResponseWrapperAdsComment <AdsCommentDto> getAdsComments(@PathVariable("ad_pk") long adPk) {
        return ResponseWrapperAdsComment.of(new ArrayList<>());
    }

    @PostMapping("/{ad_pk}/comments")
    public ResponseEntity<AdsCommentDto> addAdsComments(@PathVariable("ad_pk") long adPk,
                                                        @RequestBody AdsCommentDto adsCommentDto) {
        return ResponseEntity.ok(new AdsCommentDto());
    }
}
