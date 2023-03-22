package ru.skypro.homework.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockPart;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.CreateAdsDto;
import ru.skypro.homework.entity.Ads;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.repository.AdsCommentRepository;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.security.SecurityUtils;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.skypro.homework.controller.TestConstants.*;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Transactional
public class AdsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AdsController adsController;

    @Autowired
    private AdsRepository adsRepository;

    @Autowired
    private AdsCommentRepository adsCommentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Test
    void contextLoads() {
        Assertions.assertThat(adsController).isNotNull();
    }

    @BeforeEach
    void setUp() {

    }

    @Test
    void getAllAds() throws Exception {
        mockMvc.perform(get("/ads"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value(0))
                .andExpect(jsonPath("$.results").isEmpty());
    }

    @Test
    void addAds() throws Exception {
//        doReturn(ADS).when(adsMapper).toEntity(any(CreateAdsDto.class));
//        mockedStatic.when(SecurityUtils::getUserIdFromContext).thenReturn(ID);
//        doReturn(Optional.of(USER)).when(userRepository).findById(anyLong());
//        doReturn(IMAGE).when(imageRepository).save(any(Image.class));
//        doReturn(ADS).when(adsRepository).save(any(Ads.class));
//        doReturn(ADS_DTO).when(adsMapper).toDto(any(Ads.class));

        mockMvc.perform(multipart("/ads")
                        .file(IMAGE_FILE)
                        .part(new MockPart("properties", CREATE_ADS_DTO_JSON.toString().getBytes()))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pk").value(ID))
                .andExpect(jsonPath("$.author").value(ID))
                .andExpect(jsonPath("$.image").value(IMAGE_STRING))
                .andExpect(jsonPath("$.price").value(PRICE))
                .andExpect(jsonPath("$.title").value(TITLE))
                .andDo(print());
    }

    @Test
    void getAdsMe() {
    }

    @Test
    void getFullAd() {
    }

    @Test
    void getAdsComment() {
    }

    @Test
    void deleteAdsComment() {
    }

    @Test
    void updateComments() {
    }

    @Test
    void updateAds() {
    }

    @Test
    void updateAdsImage() {
    }

    @Test
    void removeAds() {
    }

    @Test
    void getComments() {
    }

    @Test
    void addAdsComments() {
    }

    @Test
    void getAdsImage() {
    }
}
