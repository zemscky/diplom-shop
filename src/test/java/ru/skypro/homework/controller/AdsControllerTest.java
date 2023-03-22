package ru.skypro.homework.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockPart;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.repository.AdsCommentRepository;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.security.SecurityUtils;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
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
    MockMvc mockMvc;
    @Autowired
    AdsController adsController;
    MockedStatic<SecurityUtils> mockedStatic = Mockito.mockStatic(SecurityUtils.class);
    @Autowired
    UserRepository userRepository;
    @Autowired
    ImageRepository imageRepository;
    @Autowired
    AdsRepository adsRepository;
    @Autowired
    AdsCommentRepository commentRepository;

    @Test
    void contextLoads() {
        Assertions.assertThat(adsController).isNotNull();
    }

    @BeforeEach
    void setUp() {
        imageRepository.save(IMAGE);
        userRepository.save(USER);

        mockedStatic.when(SecurityUtils::getUserDetailsFromContext).thenReturn(MY_USER_DETAILS);
        mockedStatic.when(SecurityUtils::getUserIdFromContext).thenReturn(ID);
    }

    @Test
    void getAllAds() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/ads"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value(0))
                .andExpect(jsonPath("$.results").isEmpty());
    }

    @Test
    void addAds() throws Exception {
        mockMvc.perform(multipart("/ads")
                        .file(IMAGE_FILE)
                        .part(new MockPart("properties", CREATE_ADS_DTO_JSON.toString().getBytes()))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pk").value(ID))
                .andExpect(jsonPath("$.author").value(ID))
                .andExpect(jsonPath("$.image").value(ADS_IMAGE_STRING))
                .andExpect(jsonPath("$.price").value(PRICE))
                .andExpect(jsonPath("$.title").value(TITLE))
                .andDo(print());
    }

    @Test
    void getAdsMe() throws Exception {
        mockMvc.perform(get("/ads/me"))
                .andDo(print())
                .andExpect(jsonPath("$.count").value(0))
                .andExpect(jsonPath("$.results").isEmpty());
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
