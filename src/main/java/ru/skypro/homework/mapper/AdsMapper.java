package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateAdsDto;
import ru.skypro.homework.dto.FullAdsDto;
import ru.skypro.homework.entity.Ads;
import ru.skypro.homework.entity.Image;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface AdsMapper extends WebMapper<AdsDto, Ads> {
    String ADS_IMAGES = "/ads/images/";

    @Mapping(target = "id", source = "pk")
    @Mapping(target = "author.id", source = "author")
    @Mapping(target = "images", ignore = true)
    Ads toEntity(AdsDto dto);

    @Mapping(target = "pk", source = "id")
    @Mapping(target = "author", source = "author.id")
    @Mapping(target = "imagesDto", source = "images", qualifiedByName = "imagesMapping")
    AdsDto toDto(Ads entity);

    @Mapping(target = "id", source = "pk")
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "images", ignore = true)
    Ads toEntity(CreateAdsDto dto);

    @Mapping(target = "authorFirstName", source = "author.firstName")
    @Mapping(target = "authorLastName", source = "author.lastName")
    @Mapping(target = "phone", source = "author.phone")
    @Mapping(target = "email", source = "author.email")
    @Mapping(target = "images", source = "images", qualifiedByName = "imagesMapping")
    @Mapping(target = "pk", source = "id")
    FullAdsDto toFullAdsDto(Ads entity);

    @Named("imagesMapping")
    default List<String> imagesMapping(List<Image> images) {
        return images.stream().
                map(i -> ADS_IMAGES + i.getId()).
                collect(Collectors.toList());
    }
}
