package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.AdsCommentDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.service.AdsService;

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

    @GetMapping("/{ad_pk}/comments/{id}")
    public ResponseEntity<AdsCommentDto> getComments(@PathVariable("ad_pk") int adPk,
                                                     @PathVariable("id") int id) {
        return ResponseEntity.ok(new AdsCommentDto());
    }

    @DeleteMapping("/{ad_pk}/comments/{id}")
    public ResponseEntity<HttpStatus> deleteComments(@PathVariable("ad_pk") int adPk,
                                                       @PathVariable("id") int id) {
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{ad_pk}/comments/{id}")
    public ResponseEntity<AdsCommentDto> updateComments(@PathVariable("ad_pk") int adPk,
                                                     @PathVariable("id") int id,
                                                     @RequestBody AdsCommentDto adsCommentDto) {
        return ResponseEntity.ok(new AdsCommentDto());
    }
}

