package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.User;

@Mapper
public interface UserMapper extends WebMapper<UserDto, User>{
    User toEntity(UserDto dto);
    UserDto toDto(User entity);
}
