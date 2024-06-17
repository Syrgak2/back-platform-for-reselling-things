package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.ads.AdDTO;
import ru.skypro.homework.dto.ads.CreateOrUpdateAdDTO;
import ru.skypro.homework.dto.ads.ExtendedAdDTO;
import ru.skypro.homework.model.Ad;
@Mapper
public interface AdMapper {

    AdMapper INSTANCE = Mappers.getMapper(AdMapper.class);

    @Mapping(source = "id", target = "pk")
    @Mapping(source = "imageUrl", target = "image")
    AdDTO adToAdDTO(Ad ad);


    @Mapping(source = "id", target = "pk")
    @Mapping(source = "user.firstName", target = "authorFirstName")
    @Mapping(source = "user.lastName", target = "authorLastName")
    @Mapping(source = "user.email", target = "email")
    @Mapping(source = "user.phone", target = "phone")
    ExtendedAdDTO AdToExtendAd(Ad ad);

    Ad createOrUpdateAdToAd(CreateOrUpdateAdDTO ad);
}
