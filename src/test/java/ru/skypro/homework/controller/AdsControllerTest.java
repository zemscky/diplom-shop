package ru.skypro.homework.controller;

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
