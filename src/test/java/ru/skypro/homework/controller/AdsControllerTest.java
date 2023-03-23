package ru.skypro.homework.controller;

import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockPart;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.repository.AdsCommentRepository;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.security.SecurityUtils;

import java.time.Instant;

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
    MockMvc mockMvc;
    @Autowired
    AdsController adsController;
    static MockedStatic<SecurityUtils> mockedStatic = Mockito.mockStatic(SecurityUtils.class);
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

    @BeforeAll
    static void staticMockSetUp() {
        mockedStatic.when(SecurityUtils::getUserDetailsFromContext).thenReturn(MY_USER_DETAILS);
        mockedStatic.when(SecurityUtils::getUserIdFromContext).thenReturn(ID);
    }

    @BeforeEach
    void setUp() {
        imageRepository.save(USER_IMAGE);
        userRepository.save(USER);
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
    @WithMockUser
    void getAdsMe() throws Exception {
        mockMvc.perform(get("/ads/me"))
                .andDo(print())
                .andExpect(jsonPath("$.count").value(0))
                .andExpect(jsonPath("$.results").isEmpty());
    }

    @Test
    @WithMockUser
    void getFullAd() throws Exception {
        imageRepository.save(ADS_IMAGE);
        adsRepository.save(ADS);

        mockMvc.perform(get("/ads/1"))
                .andDo(print())
                .andExpect(jsonPath("$.image").value(ADS_IMAGE_STRING))
                .andExpect(jsonPath("$.authorLastName").value(USER.getLastName()))
                .andExpect(jsonPath("$.authorFirstName").value(USER.getFirstName()))
                .andExpect(jsonPath("$.phone").value(USER.getPhone()))
                .andExpect(jsonPath("$.price").value(ADS.getPrice()))
                .andExpect(jsonPath("$.description").value(ADS.getDescription()))
                .andExpect(jsonPath("$.pk").value(ADS.getId()))
                .andExpect(jsonPath("$.title").value(ADS.getTitle()))
                .andExpect(jsonPath("$.email").value(USER.getEmail()));
    }

    @Test
    @WithMockUser
    void getAdsComment() throws Exception {
        imageRepository.save(ADS_IMAGE);
        adsRepository.save(ADS);
        commentRepository.save(ADS_COMMENT);

        mockMvc.perform(get("/ads/1/comments/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pk").value(ADS_COMMENT.getId()))
                .andExpect(jsonPath("$.author").value(ADS_COMMENT.getAuthor().getId()));
    }

    @Test
    @WithMockUser
    void deleteAdsComment() throws Exception {
        imageRepository.save(ADS_IMAGE);
        adsRepository.save(ADS);
        commentRepository.save(ADS_COMMENT);

        mockMvc.perform(delete("/ads/1/comments/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void updateComments() throws Exception {
        imageRepository.save(ADS_IMAGE);
        adsRepository.save(ADS);
        commentRepository.save(ADS_COMMENT);

        JSONObject createAdsDtoCommentJson = new JSONObject();
        createAdsDtoCommentJson.put("pk", 1);
        createAdsDtoCommentJson.put("author", 1);
        createAdsDtoCommentJson.put("text", "test");
        createAdsDtoCommentJson.put("createdAt", Instant.now());

        mockMvc.perform(patch("/ads/1/comments/1")
                        .content(createAdsDtoCommentJson.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.pk").value(ID))
                .andExpect(jsonPath("$.author").value(ADS_COMMENT.getAuthor().getId()))
                .andExpect(jsonPath("$.text").value("test"));
    }

    @Test
    @WithMockUser
    void updateAds() throws Exception {
        imageRepository.save(ADS_IMAGE);
        adsRepository.save(ADS);

        JSONObject createAdsDtoJson = new JSONObject();
        createAdsDtoJson.put("description", "new description");
        createAdsDtoJson.put("price", 20_000);
        createAdsDtoJson.put("title", "new title");

        mockMvc.perform(patch("/ads/1")
                        .content(createAdsDtoJson.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.pk").value(ID))
                .andExpect(jsonPath("$.author").value(ID))
                .andExpect(jsonPath("$.image").value(ADS_IMAGE_STRING))
                .andExpect(jsonPath("$.price").value(20_000))
                .andExpect(jsonPath("$.title").value("new title"));
    }

    @Test
    void updateAdsImage() {
    }

    @Test
    void removeAds() {
    }

    @Test
    void getComments()  {
    }

    @Test
    void addAdsComments() {
    }

    @Test
    void getAdsImage() {
    }
}
