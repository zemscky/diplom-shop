package ru.skypro.homework;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.mapper.UserMapperImpl;

import static org.assertj.core.api.Assertions.*;

public class TestMapper {
    UserMapper userMapper = new UserMapperImpl();

    @Test
    public void EntityDtoMapperTest() {
        UserDto userDto = new UserDto();
        User user = new User();

        assertThat(userMapper.
                toEntity(userDto).
                getClass()).
                isEqualTo(User.class);

        assertThat(userMapper.
                toDto(user).
                getClass()).
                isEqualTo(UserDto.class);
    }
}
