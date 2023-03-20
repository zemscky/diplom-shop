package ru.skypro.homework.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.skypro.homework.dto.CreateUserDto;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.impl.UserServiceImpl;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {


    private final MockMvc mockMvc;

    private final ObjectMapper objectMapper;

    @MockBean
    private ImageService imageService;

    @MockBean
    private UserServiceImpl userService;

    @MockBean
    private UserMapper userMapper;


    @Autowired
    UserControllerTest(MockMvc mockMvc, ObjectMapper objectMapper, ImageService imageService, UserServiceImpl userService) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.userService = userService;
    }

    @Test
    void addUser() throws Exception {

        User user = new User();
        CreateUserDto createUserDto = new CreateUserDto();

        user.setEmail("testers@mail.com");
        user.setFirstName("Tester");
        user.setLastName("Testerovich");
        user.setPassword("12345678");
        user.setPhone("+79999999999");

        createUserDto.setEmail("testers@mail.com");
        createUserDto.setFirstName("Tester");
        createUserDto.setLastName("Testerovich");
        createUserDto.setPassword("12345678");
        createUserDto.setPhone("+79999999999");

        when(userService.createUser(any())).thenReturn(user);
        when(userMapper.toCreateUserDto(any())).thenReturn(createUserDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/users").
                        contentType(MediaType.APPLICATION_JSON).
                        content(objectMapper.writeValueAsString(createUserDto))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value(user.getEmail()))
                .andExpect(jsonPath("$.password").value(user.getPassword()))
                .andExpect(jsonPath("$.firstName").value(user.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(user.getLastName()))
                .andExpect(jsonPath("$.phone").value(user.getPhone()));
    }
}