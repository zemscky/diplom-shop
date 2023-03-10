package ru.skypro.homework.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.entity.Ads;
import ru.skypro.homework.mapper.AdsMapper;
import ru.skypro.homework.repository.AdsRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class AdsServiceImplTest {

    @Mock
    private AdsRepository mockAdsRepository;
    private AdsServiceImpl adsServiceTest;
    private AdsMapper adsMapperTest;

    @BeforeEach
    void setUp() {
        Ads a1 = new Ads();
        Ads a2 = new Ads();
        Ads a3 = new Ads();

        a1.setId(1);
        a2.setId(2);
        a3.setId(3);

        ArrayList<Ads> testAds = (ArrayList<Ads>) List.of(a1, a2, a3);

        Mockito.when(mockAdsRepository.findAll()).thenReturn(testAds);

        AdsDto adsDto1 = new AdsDto();
        AdsDto adsDto2 = new AdsDto();
        AdsDto adsDto3 = new AdsDto();

        adsDto1.setPk(1);
        adsDto2.setPk(2);
        adsDto3.setPk(3);

        ArrayList<AdsDto> testAdsDto = (ArrayList<AdsDto>) List.of(adsDto1, adsDto2, adsDto3);

        Mockito.when(adsMapperTest.toDto(testAds)).thenReturn(testAdsDto);
    }

    @Test
    void getAllAds() {
//        Assertions.assertEquals();
    }

    @Test
    void getAdsMe() {
    }

    @Test
    void getFullAd() {
    }

    @Test
    void addAds() {
    }

    @Test
    void getComments() {
    }

    @Test
    void deleteComments() {
    }

    @Test
    void updateComments() {
    }

    @Test
    void updateAds() {
    }

    @Test
    void removeAds() {
    }

    @Test
    void getAdsComments() {
    }

    @Test
    void addAdsComments() {
    }
}