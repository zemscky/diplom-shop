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
    @Mapping(target = "images", expression = "java(setImages(adsDto.getImagesDto()))")  //возможно здесь нужен ignore?
    Ads toEntity(AdsDto dto);

    @Mapping(target = "pk", source = "id")
    @Mapping(target = "author", source = "author.id")
    @Mapping(target = "imagesDto", expression = "java(setImagesDto(ads.getImages()))")
    AdsDto toDto(Ads entity);

    @Mapping(target = "id", source = "pk")
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "images", ignore = true)
    Ads toEntity(CreateAdsDto dto);

    @Mapping(target = "authorFirstName", source = "author.firstName")
    @Mapping(target = "authorLastName", source = "author.lastName")
    @Mapping(target = "phone", source = "author.phone")
    @Mapping(target = "email", source = "author.email")
    @Mapping(target = "imagesDto", expression = "java(setImagesDto(ads.getImages()))")
    FullAdsDto toFullAdsDto(Ads entity);


    default List<String> setImagesDto(List<Image> images) {
        if (images == null || images.size() == 0) {
            return null;
        }
        return images
                .stream()
                .map(Image::getFilePath)
                .collect(Collectors.toList());
    }

    default List<Image> setImages(List<String> imagesDto) {
        if (imagesDto == null || imagesDto.size() == 0) {
            return null;
        }
        List<Image> images = new ArrayList<>();
        for (String s : imagesDto) {
            Image image = new Image();
            image.setFilePath(s);
            images.add(image);
        }
        return images;
    }

    Collection<Ads> toEntityList(Collection<AdsDto> adsDtoList);

    Collection<AdsDto> toDtoList(Collection<Ads> adsList);
}
