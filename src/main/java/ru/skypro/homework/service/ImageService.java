package ru.skypro.homework.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.Image;

import java.io.IOException;

public interface ImageService {
    /**
     * Сохранение картинки в БД
     *
     * @param imageFile Объект картинка
     * @return Images
     */
    Image uploadImage(MultipartFile imageFile) throws IOException;

    /**
     * Получение картинки по ID
     *
     * @param id Id картинки
     * @return Images
     */
    Image getImageById(long id);

    /**
     * Updates Image instance`s info (fields: fileSize, mediaType, data).
     * The purpose is to replace an old image for an ad on the website with a new one.
     *
     * @param id Image instance`s id
     * @param image image file
     * @return ResponseEntity<byte[]>
     */
    ResponseEntity<byte[]> updateAdsImage(long id, MultipartFile image);
}
