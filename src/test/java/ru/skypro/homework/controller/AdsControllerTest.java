package ru.skypro.homework.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.repository.AdsRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Transactional
public class AdsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AdsController adsController;

    @Test
    void contextLoads() {
        Assertions.assertThat(adsController).isNotNull();
    }

    @Test
    void testGet() throws Exception {
        mockMvc.perform(get("/ads"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value(0))
                .andExpect(jsonPath("$.results").isEmpty());
    }

}
