package ru.skypro.homework.service.impl;

import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.Ads;
import ru.skypro.homework.entity.AdsComment;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.mapper.AdsMapper;
import ru.skypro.homework.repository.AdsCommentRepository;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class AdsServiceImpl implements AdsService {

    private final UserService userService;
    private final ImageService imageService;
    private final AdsRepository adsRepository;
    private final AdsCommentRepository adsCommentRepository;
    private final AdsMapper adsMapper;

    public AdsServiceImpl(UserService userService, ImageService imageService, AdsRepository adsRepository,
                          AdsCommentRepository adsCommentRepository,
                          AdsMapper adsMapper) {
        this.userService = userService;
        this.imageService = imageService;
        this.adsRepository = adsRepository;
        this.adsCommentRepository = adsCommentRepository;
        this.adsMapper = adsMapper;
    }

    @Override
    public ResponseWrapper<AdsDto> getAllAds() {
        Collection<AdsDto> allAdsDto = adsMapper.toDto(adsRepository.findAll());
        return ResponseWrapper.of(allAdsDto);
    }

    @Override // требует доработки на следующих этапах
    public ResponseWrapper<AdsDto> getAdsMe() {
        return ResponseWrapper.of(new ArrayList<AdsDto>());
    }

    @Override
    public ResponseEntity<FullAdsDto> getFullAd(Long adId) {
        Ads ads = adsRepository.findById(adId).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "The ad was not found"));
        return ResponseEntity.ok(adsMapper.toFullAdsDto(ads));
    }

    @SneakyThrows
    @Override
    public AdsDto addAds(CreateAdsDto createAdsDto, MultipartFile... imageFiles) {

        Ads ads = adsMapper.toEntity(createAdsDto); //передали id, title, description, price
//        User user = userService.getUserById(getUserIdFromContext()); //найти Id юзеоа, создающего объявление

        ads.setAuthor(new User());

        List<Image> images = new ArrayList<>();
        for (MultipartFile imageFile : imageFiles) {
            images.add(imageService.uploadImage(imageFile));
        }
        ads.setImages(images);

        return adsMapper.toDto(adsRepository.save(ads));
    }

    @Override
    public AdsComment getAdsComment(long adPk, long id) {
        AdsComment adsComment = adsCommentRepository.findByIdAndAdId(id, adPk)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Комментарий с id %d, " +
                                "принадлежащий объявлению с id %d не найден!", id, adPk)));
        return adsComment;
    }

    @Override // Требует доработок на следующем этапе с учётом авторизации пользователей
    public ResponseEntity<HttpStatus> deleteComments(long adPk, long id) {
        // Возможно, нам понадобится доставать комментарий из бд в будущем
        AdsComment comment = adsCommentRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "The comment was not found"));
        adsCommentRepository.deleteById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Override
    public AdsComment updateComments(int adPk, int id, AdsComment adsCommentUpdated) {
        AdsComment adsComment = getAdsComment(adPk, id);

//        доработка в 4 этапе,  связано с авторизацией

        adsComment.setText(adsCommentUpdated.getText());

        return adsCommentRepository.save(adsComment);
    }

    @Override
    public ResponseEntity<AdsDto> updateAds(Long adId, CreateAdsDto createAdsDto) {
        // Метод маппера здесь бесполезен, т.к. создаёт энтити Ads без id и автора.
        // По-любому сеттеры понадобятся
        Ads ads = adsRepository.findById(adId).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "The ad was not found"));

        ads.setTitle(createAdsDto.getTitle());
        ads.setDescription(createAdsDto.getDescription());
        ads.setPrice(createAdsDto.getPrice());

        ads = adsRepository.save(ads);
        return ResponseEntity.ok(adsMapper.toDto(ads));
    }

}
