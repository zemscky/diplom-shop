package ru.skypro.homework.service.impl;

import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.Ads;
import ru.skypro.homework.entity.AdsComment;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.mapper.AdsCommentMapper;
import ru.skypro.homework.mapper.AdsMapper;
import ru.skypro.homework.repository.AdsCommentRepository;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.security.SecurityUtils;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

import java.time.Instant;
import java.util.Collection;

import static ru.skypro.homework.security.SecurityUtils.*;


@Service
public class AdsServiceImpl implements AdsService {

    private final UserService userService;
    private final ImageService imageService;
    private final AdsRepository adsRepository;
    private final AdsCommentRepository adsCommentRepository;
    private final AdsMapper adsMapper;
    private final AdsCommentMapper adsCommentMapper;

    public AdsServiceImpl(UserService userService, ImageService imageService, AdsRepository adsRepository,
                          AdsCommentRepository adsCommentRepository,
                          AdsMapper adsMapper, AdsCommentMapper adsCommentMapper) {
        this.userService = userService;
        this.imageService = imageService;
        this.adsRepository = adsRepository;
        this.adsCommentRepository = adsCommentRepository;
        this.adsMapper = adsMapper;
        this.adsCommentMapper = adsCommentMapper;
    }

    @Override
    public Collection<Ads> getAllAds() {
        return adsRepository.findAll();
    }

    @SneakyThrows
    @Override
    public Ads addAds(CreateAdsDto createAdsDto, MultipartFile imageFile) {
        Ads ads = adsMapper.toEntity(createAdsDto); //передали title, description, price
        User user = userService.getUserById(getUserIdFromContext());
        ads.setAuthor(user);
        ads.setImage(imageService.uploadImage(imageFile));
        return adsRepository.save(ads);
    }

    @Override
    public Collection<Ads> getAdsMe() {
        return adsRepository.findAllByAuthorId(getUserIdFromContext());
    }

    public Ads getAdsById(Long adId) {
        return adsRepository.findById(adId).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "The ad was not found"));
    }

    public Ads removeAdsById(Long adId) {
        Ads ads = getAdsById(adId);
        checkPermissionToAds(ads);
        adsCommentRepository.deleteAdsCommentsByAdsId(adId);
        adsRepository.deleteById(adId);
        return ads;
    }

    @Override
    public AdsComment getAdsComment(long adPk, long id) {
        AdsComment adsComment = adsCommentRepository.findByIdAndAdId(id, adPk)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Комментарий с id %d, " +
                        "принадлежащий объявлению с id %d не найден!", id, adPk)));
        return adsComment;
    }

    @Override
    public Collection<AdsComment> getComments(long adPk) {
        return adsCommentRepository.findAllByAdId(adPk);
    }

    @Override
    public AdsComment addAdsComments(long adPk, AdsCommentDto adsCommentDto) {
        AdsComment adsComment = adsCommentMapper.toEntity(adsCommentDto);

        User user = userService.getUserById(getUserIdFromContext());

        adsComment.setAuthor(user);
        adsComment.setAd(getAdsById(adPk));
        adsComment.setCreatedAt(Instant.now());

        return adsCommentRepository.save(adsComment);
    }

    @Override
    public AdsComment deleteAdsComment(long adPk, long id) {
        AdsComment comment = getAdsComment(adPk, id);

        checkPermissionToAdsComment(comment);

        adsCommentRepository.delete(comment);

        return comment;
    }

    @Override
    public AdsComment updateComments(int adPk, int id, AdsComment adsCommentUpdated) {
        AdsComment adsComment = getAdsComment(adPk, id);

        SecurityUtils.checkPermissionToAdsComment(adsComment);

        adsComment.setText(adsCommentUpdated.getText());

        return adsCommentRepository.save(adsComment);
    }

    @Override
    @SneakyThrows
    public void updateAdsImage(long id, MultipartFile image) {
        Ads ads = getAdsById(id);

        checkPermissionToAds(ads);

        ads.setImage(imageService.uploadImage(image));

        adsRepository.save(ads);
    }

    @Override
    public Ads updateAds(Long adId, CreateAdsDto createAdsDto) {
        Ads ads = getAdsById(adId);

        checkPermissionToAds(ads);

        ads.setTitle(createAdsDto.getTitle());
        ads.setDescription(createAdsDto.getDescription());
        ads.setPrice(createAdsDto.getPrice());

        return adsRepository.save(ads);
    }

}
