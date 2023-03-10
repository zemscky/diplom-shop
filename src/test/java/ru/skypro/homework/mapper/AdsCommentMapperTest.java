package ru.skypro.homework.mapper;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.skypro.homework.dto.AdsCommentDto;
import ru.skypro.homework.entity.AdsComment;

import java.time.Instant;

public class AdsCommentMapperTest {
    AdsCommentMapper adsCommentMapper = new AdsCommentMapperImpl();

    @Test
    void test() {
        AdsComment entity = new AdsComment();
        AdsCommentDto dto;
        entity.setCreatedAt(Instant.now());

        dto = adsCommentMapper.toDto(entity);
        Assertions.assertThat(dto.getClass()).
                isEqualTo(AdsCommentDto.class).
                isNotNull();

        entity = adsCommentMapper.toEntity(dto);
        Assertions.assertThat(entity.getClass()).
                isEqualTo(AdsComment.class).
                isNotNull();

        Assertions.assertThat(dto.getCreatedAt()).isEqualTo(entity.getCreatedAt().toString());
    }
}
