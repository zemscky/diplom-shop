package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.FullAdsDto;
import ru.skypro.homework.dto.ResponseWrapperAds;
import ru.skypro.homework.service.AdsService;

@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
@RestController
@RequestMapping("/ads")
public class AdsController {

    private final AdsService adsService;

    @GetMapping
    public ResponseWrapperAds getAllAds() {
        return new ResponseWrapperAds();
    }

    @GetMapping("/me{userId}")
    public ResponseWrapperAds getAdsMe(@PathVariable("userId") Long userId) {
        return new ResponseWrapperAds();
    }

    @GetMapping("/{adId}") // Разобраться: айди юзера или рекламного объявления. Я склоняюсь к тому,
    // что, судя по названию и возвращаемому значению в файле openapi, это ПОЛНОЕ объявление.
    // То есть выбираем из списка и открываем.
    public ResponseEntity<FullAdsDto> getFullAd(@PathVariable("adId") Long adId) {
        return ResponseEntity.ok(new FullAdsDto());
    }

    @PostMapping("/{userId}")
    public ResponseEntity<AdsDto> addAds(@PathVariable("userId") Long userId,
                                         @RequestBody AdsDto adsDto) {
        return ResponseEntity.ok(new AdsDto());
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<AdsDto> updateAds(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(new AdsDto());
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> removeAds(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok("Объявление успешно удалено");
    }
}
