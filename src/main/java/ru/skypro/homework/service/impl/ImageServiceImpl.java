package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.webjars.NotFoundException;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.service.ImageService;

import javax.transaction.Transactional;
import java.io.IOException;

@Transactional
@RequiredArgsConstructor
@Service
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;

    @Override
    public Image uploadImage(MultipartFile imageFile) throws IOException {
        Image image = new Image();

        image.setData(imageFile.getBytes());
        image.setFileSize(imageFile.getSize());
        image.setMediaType(imageFile.getContentType());
        image.setData(imageFile.getBytes());

        return imageRepository.save(image);
    }

    @Override
    public Image getImageById(long id) {
        return imageRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Image with id " + id + " not found!"));
    }

    @SneakyThrows
    @Override
    public ResponseEntity<byte[]> updateAdsImage(long id, MultipartFile image) {
        if (image == null) {
            throw new NotFoundException("New ad image not uploaded");
        }

        Image img = getImageById(id);

        img.setFileSize(image.getSize());
        img.setMediaType(image.getContentType());
        img.setData(image.getBytes());

        return ResponseEntity.ok(imageRepository.save(img).getData());
    }
}
