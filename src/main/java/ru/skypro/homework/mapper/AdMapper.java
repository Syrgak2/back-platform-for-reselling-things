package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.ads.AdDTO;
import ru.skypro.homework.dto.ads.CreateOrUpdateAd;
import ru.skypro.homework.dto.ads.ExtendedAd;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;
@Mapper
public interface AdMapper {

    AdMapper INSTANCE = Mappers.getMapper(AdMapper.class);

    @Mapping(source = "id", target = "pk")
    @Mapping(source = "image.url", target = "image")
    AdDTO adToAdDTO(Ad ad);


    @Mapping(source = "id", target = "pk")
    @Mapping(source = "user.firstName", target = "authorFirstName")
    @Mapping(source = "user.authorLastName", target = "authorLastName")
    @Mapping(source = "user.email", target = "email")
    @Mapping(source = "user.phone", target = "phone")
    ExtendedAd AdToExtendAd(Ad ad);

    @Mapping(target = "id", ignore = true)
    Ad createOrUpdateAdToAd(CreateOrUpdateAd ad);
}
