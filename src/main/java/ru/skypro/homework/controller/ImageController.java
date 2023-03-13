package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.service.ImageService;

import javax.validation.constraints.NotNull;

@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
@RestController
@RequestMapping("/image")
@Tag(name = "Изображения", description = "ImageController")
public class ImageController {

    private final ImageService imageService;

    @Operation(summary = "updateAdsImage", description = "updateAdsImage")
    @PatchMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> updateAdsImage(@PathVariable("id") long id,
                                                @NotNull @RequestBody MultipartFile image) {
        return imageService.updateAdsImage(id, image);
    }
}
