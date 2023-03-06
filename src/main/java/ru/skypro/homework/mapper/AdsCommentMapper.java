package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.AdsCommentDto;
import ru.skypro.homework.entity.AdsComment;


@Mapper
public interface AdsCommentMapper extends WebMapper<AdsCommentDto, AdsComment> {
    @Mapping(target = "pk", ignore = true )
    AdsCommentDto adsCommentToAdsCommentDto(AdsComment adsComment);
}
