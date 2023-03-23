package ru.skypro.homework.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.repository.UserRepository;

import java.time.Instant;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ActiveProfiles("test")
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Transactional
public class UserControllerTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    UserController userController;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    public void setup() {
//        mockMvc = MockMvcBuilders
//                .webAppContextSetup(context)
//                .apply(springSecurity()) // enable security for the mock set up
//                .build();
    }

    @Test
    void contextLoads() {
        Assertions.assertThat(userController).isNotNull();
    }

    @WithMockUser(value = "a@mail.ru", password = "12345678")
    @Test
    void update() throws Exception {
        userRepository.save(new User(1L, "fgsfd", "fdsfsd", "a@mail.ru", "12345678",
                "446486568", null, Instant.now(), null, Role.USER));

        User user = new User();
        UserDto userDto = new UserDto();

        user.setEmail("a@mail.ru");
        user.setFirstName("Ivan");
        user.setLastName("Ivanov");
        user.setPhone("+79991254698");
        user.setRole(Role.USER);
        user.setPassword("123");

        userDto.setEmail("a@mail.ru");
        userDto.setFirstName("Ivan");
        userDto.setLastName("Ivanov");
        userDto.setPhone("+79991254698");

        mockMvc.perform(MockMvcRequestBuilders.patch("/users/me").with(httpBasic("a@mail.ru","12345678"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value(user.getEmail()))
                .andExpect(jsonPath("$.firstName").value(user.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(user.getLastName()))
                .andExpect(jsonPath("$.phone").value(user.getPhone()));
    }

}


