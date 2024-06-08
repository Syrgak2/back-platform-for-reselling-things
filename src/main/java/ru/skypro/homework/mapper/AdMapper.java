package ru.skypro.homework.mapper;

import ru.skypro.homework.dto.ads.AdDTO;
import ru.skypro.homework.dto.ads.CreateOrUpdateAd;
import ru.skypro.homework.dto.ads.ExtendedAd;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;

public class AdMapper {
    public static AdDTO toAd(Ad ad) {
        if (ad == null) {
            return null;
        }

        AdDTO adDTO = new AdDTO();

        adDTO.setTitle(ad.getTitle());
        adDTO.setPrice(ad.getPrice());
        adDTO.setPk(ad.getId());
        adDTO.setAuthor(ad.getUser().getId());
        adDTO.setImage(adDTO.getImage());
        return adDTO;
    }

    public static ExtendedAd toExtendAd(Ad ad, User user) {
        if (ad == null) {
            return null;
        }

        ExtendedAd extendedAd = new ExtendedAd();

        extendedAd.setPk(ad.getId());
        extendedAd.setPrice(ad.getPrice());
        extendedAd.setTitle(ad.getTitle());
        extendedAd.setDescription(ad.getDescription());
        extendedAd.setAuthorFirstName(user.getFirstName());
        extendedAd.setAuthorLastName(user.getLastName());
        extendedAd.setPhone(user.getPhone());
        extendedAd.setEmail(user.getEmail());
        return extendedAd;
    }

    public static Ad toAdModel(CreateOrUpdateAd ad) {
        if (ad == null) {
            return null;
        }
        Ad adModel = new Ad();

        adModel.setTitle(ad.getTitle());
        adModel.setDescription(ad.getDescription());
        adModel.setPrice(ad.getPrice());
        return adModel;
    }
}
