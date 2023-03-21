package ru.skypro.homework.controller;

<<<<<<< HEAD
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.mock.web.MockPart;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.skypro.homework.dto.CreateAdsDto;
import ru.skypro.homework.entity.Ads;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.mapper.AdsCommentMapper;
import ru.skypro.homework.mapper.AdsMapper;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.AdsCommentRepository;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.security.SecurityUtils;
import ru.skypro.homework.security.UserDetailsServiceImpl;
import ru.skypro.homework.service.impl.AdsServiceImpl;
import ru.skypro.homework.service.impl.AuthServiceImpl;
import ru.skypro.homework.service.impl.ImageServiceImpl;
import ru.skypro.homework.service.impl.UserServiceImpl;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.skypro.homework.controller.TestConstants.*;

@WebMvcTest(AdsController.class)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc(addFilters = false)
class AdsControllerTestWebMvc {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    @InjectMocks
    private AdsController adsController;
    @SpyBean
    private AdsServiceImpl adsService;
    @SpyBean
    private ImageServiceImpl imageService;
    @SpyBean
    private UserServiceImpl userService;
    @MockBean
    private AdsRepository adsRepository;
    @MockBean
    private AdsCommentRepository adsCommentRepository;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private ImageRepository imageRepository;
    @MockBean
    private AdsMapper adsMapper;
    @MockBean
    private AdsCommentMapper adsCommentMapper;
    @MockBean
    private AuthServiceImpl authService;
    @MockBean
    private UserMapper userMapper;
    @MockBean
    private PasswordEncoder passwordEncoder;
    @SpyBean
    private UserDetailsServiceImpl userDetailsService;
    private final MockedStatic<SecurityUtils> mockedStatic = Mockito.mockStatic(SecurityUtils.class);

    @Autowired
    AdsControllerTestWebMvc(WebApplicationContext webApplicationContext, ObjectMapper objectMapper) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        this.objectMapper = objectMapper;
    }


    //        mockMvc.perform(MockMvcRequestBuilders
//                        .get("/ads") //send
//                        .content(createAdsDto.toString())
//                        .content(imageFile.getBytes())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.MULTIPART_FORM_DATA))
//                .andExpect(status().isOk()) //receive
//                .andExpect(jsonPath("$.pk").value(id))
//                .andExpect(jsonPath("$.author").value(id))
//                .andExpect(jsonPath("$.image").value(imageString))
//                .andExpect(jsonPath("$.price").value(price))
//                .andExpect(jsonPath("$.title").value(title))
//                .andExpect(jsonPath("$.description").value(description))
//                .andDo(print());


    @BeforeEach
    void setUp() {

    }

    @Test
    void getAllAds() {

    }

    @Test
    void addAds() throws Exception {
        doReturn(ADS).when(adsMapper).toEntity(any(CreateAdsDto.class));
        mockedStatic.when(SecurityUtils::getUserIdFromContext).thenReturn(ID);
        doReturn(Optional.of(USER)).when(userRepository).findById(anyLong());
        doReturn(IMAGE).when(imageRepository).save(any(Image.class));
        doReturn(ADS).when(adsRepository).save(any(Ads.class));
        doReturn(ADS_DTO).when(adsMapper).toDto(any(Ads.class));

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
=======
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import ru.skypro.homework.repository.AdsRepository;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AdsControllerTest {
    @LocalServerPort
    private int port;

    @MockBean
    private AdsRepository adsRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @InjectMocks
    private AdsController adsController;

    @Test
    void contextLoads() {
        Assertions.assertThat(adsController).isNotNull();
    }

    private String buildUrl(String otherPath) {
        return "http://localhost:" + port + "/" + otherPath;
    }


}
>>>>>>> origin/dev2
