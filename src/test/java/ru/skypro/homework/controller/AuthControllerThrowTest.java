package ru.skypro.homework.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import ru.skypro.homework.dto.LoginReqDto;
import ru.skypro.homework.dto.RegisterReqDto;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;

import javax.validation.ValidationException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.skypro.homework.controller.AuthControllerTest.getMockUser;


@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class AuthControllerThrowTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    AuthService authService;

    @MockBean
    private UserRepository userRepository;

    private static ObjectMapper mapper = new ObjectMapper();


    @Test
    public void testAuthenticateUserDoesNotExistThrow() throws Exception {
        User user = getMockUser();
        LoginReqDto req = new LoginReqDto();
        req.setUsername(user.getEmail());
        req.setPassword(user.getPassword());
        String json = mapper.writeValueAsString(req);
        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .content(json))
                .andExpect(status().is(403));
        assertThrows(UsernameNotFoundException.class,
                () -> userDetailsService.loadUserByUsername(req.getUsername()),
                "User test@mail.com does not exist!");
    }

    @Test
    public void testRegisterUserIsAlreadyRegisteredThrow() throws Exception {
        Mockito.when(userRepository.existsByEmailIgnoreCase(Mockito.anyString())).thenReturn(true);
        User user = getMockUser();
        RegisterReqDto req = new RegisterReqDto();
        req.setUsername(user.getEmail());
        req.setPassword(user.getPassword());
        req.setFirstName(user.getFirstName());
        req.setLastName(user.getLastName());
        req.setPhone(user.getPhone());
        req.setRole(null);
        String json = mapper.writeValueAsString(req);
        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .content(json))
                .andExpect(status().is(400));
        assertThrows(ValidationException.class,
                () -> authService.register(req),
                "User test@mail.com is already registered!");
    }
}
