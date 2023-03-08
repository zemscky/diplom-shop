package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateAdsDto;
import ru.skypro.homework.dto.FullAdsDto;
import ru.skypro.homework.entity.Ads;
import ru.skypro.homework.entity.Image;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface AdsMapper extends WebMapper<AdsDto, Ads> {

    @Mapping(target = "id", source = "pk")
    @Mapping(target = "author.id", source = "author")
    @Mapping(target = "images", source = "imagesDto")
    @Mapping(target = "adsComments", ignore = true)
    Ads toEntity(AdsDto dto);

    @Mapping(target = "pk", source = "id")
    @Mapping(target = "author", source = "author.id")
    @Mapping(target = "imagesDto", source = "images")
    AdsDto toDto(Ads entity);

    @Mapping(target = "id", source = "pk")
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "images", ignore = true)
    @Mapping(target = "adsComments", ignore = true)
    Ads toEntity(CreateAdsDto dto);

    @Mapping(target = "authorFirstName", source = "author.firstName")
    @Mapping(target = "authorLastName", source = "author.lastName")
    @Mapping(target = "phone", source = "author.phone")
    @Mapping(target = "email", source = "author.email")
    @Mapping(target = "imagesDto", source = "images")
    @Mapping(target = "pk", source = "id")
    FullAdsDto toFullAdsDto(Ads entity);

    Collection<Ads> toEntityList(Collection<AdsDto> adsDtoList); //это обязательно?

    Collection<AdsDto> toDtoList(Collection<Ads> adsList);//это обязательно?
}
