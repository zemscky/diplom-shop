package ru.skypro.homework.service.impl;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.security.MyUserDetails;
import ru.skypro.homework.security.UserDetailsServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static ru.skypro.homework.security.SecurityUtils.getUserDetailsFromContext;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Spy
    private PasswordEncoder passwordEncoder;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private Authentication authentication;

    @Mock
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private ImageServiceImpl imageService;

    @Mock
    private ImageRepository imageRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void createUser() {
        User testUser = new User();
        testUser.setPassword("123456789");

        when(userRepository.existsByEmailIgnoreCase(any())).thenReturn(false);
        when(passwordEncoder.encode(any())).thenReturn("12345678");
        when(userRepository.save(any(User.class))).thenReturn(testUser);

        User user = userService.createUser(testUser);

        assertEquals(testUser, user);
        assertEquals(testUser.getPassword(), user.getPassword());
    }

    @Test
    void getUsers() {
        User testUser = new User();
        testUser.setId(1L);
        testUser.setEmail("testEmail");
        testUser.setPassword("12345");
        testUser.setRole(Role.USER);
        SecurityContextHolder.setContext(securityContext);

        when(userRepository.findByEmailIgnoreCase(testUser.getEmail())).thenReturn(Optional.of(testUser));
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(getUserDetailsFromContext()).thenReturn(new MyUserDetails(testUser));

        User user = userService.getUsers();

        assertEquals(user, testUser);

        verify(userRepository, times(1)).findByEmailIgnoreCase(testUser.getEmail());
    }

    @Test
    void getUserById() {
        User testUser = new User();

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(testUser));

        User user = userService.getUserById(1L);

        assertEquals(testUser, user);

        verify(userRepository, times(1)).findById(anyLong());

    }

    @Test
    void getUserByIdThrows() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> userService.getUserById(1L));
    }

    @Test
    void updateUser() {
        UserDto dto = new UserDto();

        dto.setId(123L);
        dto.setFirstName("abcd");
        dto.setLastName("efg");
        dto.setPhone("88006663636");

        User user = new User();
        user.setId(123L);
        user.setEmail("abc@mail.ru");
        user.setPassword("12345");
        user.setRole(Role.USER);

        SecurityContextHolder.setContext(securityContext);

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(getUserDetailsFromContext()).thenReturn(new MyUserDetails(user));
        when(userRepository.findById(123L)).thenReturn(Optional.of(user));

        userService.updateUser(dto);

        assertEquals(user.getId(), dto.getId());
        assertEquals(user.getFirstName(), dto.getFirstName());
        assertEquals(user.getLastName(), dto.getLastName());
        assertEquals(user.getPhone(), dto.getPhone());
    }

    @Test
    void newPassword() {
        User testUser = new User();

        testUser.setId(1L);
        testUser.setEmail("abc@mail.ru");
        testUser.setPassword("12345");
        testUser.setRole(Role.USER);

        when(securityContext.getAuthentication()).thenReturn(authentication);

        SecurityContextHolder.setContext(securityContext);

        when(getUserDetailsFromContext()).thenReturn(new MyUserDetails(testUser));
        when(passwordEncoder.matches(any(), any())).thenReturn(true);
        when(passwordEncoder.encode(any())).thenReturn("12345678");

        userService.newPassword("12345678", "87654321");

        assertTrue(true);
    }

    @Test
    void updateRoleUser() {
        User testUser = new User();
        testUser.setEmail("b@maiil.ru");

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(testUser));
        when(userRepository.save(any(User.class))).thenReturn(testUser);

        User user = userService.updateRole(1L, Role.USER);

        assertEquals(testUser, user);

        verify(userRepository, times(1)).findById(anyLong());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    @SneakyThrows
    void updateUserImage() {
        User testUser = new User();

        testUser.setId(1L);
        testUser.setEmail("abc@mail.ru");
        testUser.setPassword("12345");
        testUser.setRole(Role.USER);

        MultipartFile multipartFile = mock(MultipartFile.class);
        SecurityContextHolder.setContext(securityContext);

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(getUserDetailsFromContext()).thenReturn(new MyUserDetails(testUser));
        when(imageService.uploadImage(multipartFile)).thenReturn(new Image(12, 123, "media", new byte[1]));
        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));
        when(userRepository.save(any())).thenReturn(testUser);

        String webPath = userService.updateUserImage(multipartFile);

        assertEquals("/users/image/" + 12, webPath);

        verify(imageService, times(1)).uploadImage(any());
    }
}
