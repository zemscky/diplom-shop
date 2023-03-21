package ru.skypro.homework.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.config.TestConfig;
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

import javax.annotation.Resource;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
//@ContextConfiguration(
//        classes = { TestConfig.class },
//        loader = AnnotationConfigContextLoader.class)
@Transactional
public class AdsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @LocalServerPort
    private int port;

//    @MockBean
    @Resource
    private AdsRepository adsRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @InjectMocks
    private AdsController adsController;

    @Autowired
    private ObjectMapper objectMapper;
    @SpyBean
    private AdsServiceImpl adsService;
    @SpyBean
    private ImageServiceImpl imageService;
    @SpyBean
    private UserServiceImpl userService;
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


    @Test
    void contextLoads() {
        Assertions.assertThat(adsController).isNotNull();
    }

    private String buildUrl(String otherPath) {
        return "http://localhost:" + port + "/" + otherPath;
    }


}
