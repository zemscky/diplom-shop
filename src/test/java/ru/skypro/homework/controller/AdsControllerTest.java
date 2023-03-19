package ru.skypro.homework.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.checkerframework.common.value.qual.StaticallyExecutable;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockPart;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateAdsDto;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.entity.Ads;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.mapper.AdsCommentMapper;
import ru.skypro.homework.mapper.AdsMapper;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.AdsCommentRepository;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.security.SecurityUtils;
import ru.skypro.homework.security.UserDetailsServiceImpl;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;
import ru.skypro.homework.service.impl.AdsServiceImpl;
import ru.skypro.homework.service.impl.AuthServiceImpl;
import ru.skypro.homework.service.impl.ImageServiceImpl;
import ru.skypro.homework.service.impl.UserServiceImpl;

import javax.servlet.http.Part;
import java.time.Instant;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.skypro.homework.security.SecurityUtils.getUserIdFromContext;

@WebMvcTest(AdsController.class)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc(addFilters = false)
class AdsControllerTest {

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
    AdsControllerTest(WebApplicationContext webApplicationContext, ObjectMapper objectMapper) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        this.objectMapper = objectMapper;
    }

    @BeforeEach
    void setUp() {

    }

    @Test
    void getAllAds() {

    }

    @Test
    @WithMockUser(authorities = "USER")
    void addAds() throws Exception {
        MockMultipartFile imageFile = new MockMultipartFile("image", new byte[0]);

        Image image = new Image(1, 12, "png", imageFile.getBytes());
//        doReturn(image).when(imageService).uploadImage(any());
        doReturn(image).when(imageRepository).save(any(Image.class));

        User user = new User(1, "fn", "ln", "email",
                "password", "phone", "city", Instant.now(),
                null, Role.USER);
        doReturn(Optional.of(user)).when(userRepository).findById(anyLong());
//        doReturn(1L).;
//        when(SecurityUtils.getUserIdFromContext()).thenReturn(1L);
//        try (MockedStatic<SecurityUtils> mockedStatic = Mockito.mockStatic(SecurityUtils.class)) {
//            mockedStatic.when(SecurityUtils::getUserIdFromContext).thenReturn(1L);
//        }
        mockedStatic.when(SecurityUtils::getUserIdFromContext).thenReturn(1L);

        Long id = 1L;
        String description = "description";
        int price = 10;
        String title = "title";
        String imageString = "/ads/image/1";

//        CreateAdsDto createAdsDto = new CreateAdsDto();
//        createAdsDto.setDescription(description);
//        createAdsDto.setPrice(price);
//        createAdsDto.setTitle(title);

        JSONObject createAdsDto = new JSONObject();
        createAdsDto.put("description", description);
        createAdsDto.put("price", price);
        createAdsDto.put("title", title);

        AdsDto adsDto = new AdsDto();
        adsDto.setPk(id);
        adsDto.setAuthor(1);
        adsDto.setImage(imageString);
        adsDto.setPrice(price);
        adsDto.setTitle(title);
        adsDto.setDescription(description);


        Ads ads = new Ads(id, title, description, price, user, image);
        doReturn(ads).when(adsMapper).toEntity(any(CreateAdsDto.class));
        doReturn(ads).when(adsRepository).save(any(Ads.class));

        doReturn(adsDto).when(adsMapper).toDto(any(Ads.class));

//        JSONObject userObject = new JSONObject();
//        userObject.put("name", name);
//
//        User user = new User();
//        user.setId(id);
//        user.setName(name);
//
//        when(userRepository.save(any(User.class))).thenReturn(user);
//        when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(user));

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

        mockMvc.perform(multipart("/ads")
                        .file(imageFile)
                        .part(new MockPart("properties", createAdsDto.toString().getBytes()))
//                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pk").value(id))
                .andExpect(jsonPath("$.author").value(id))
                .andExpect(jsonPath("$.image").value(imageString))
                .andExpect(jsonPath("$.price").value(price))
                .andExpect(jsonPath("$.title").value(title))
//                .andExpect(jsonPath("$.description").value(description))
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