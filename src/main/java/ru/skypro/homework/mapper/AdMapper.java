package ru.skypro.homework.mapper;

import ru.skypro.homework.dto.ads.Ad;
import ru.skypro.homework.dto.ads.CreateOrUpdateAd;
import ru.skypro.homework.dto.ads.ExtendedAd;
import ru.skypro.homework.model.AdModel;
import ru.skypro.homework.model.UserModel;

public class AdMapper {
    public static Ad toAd(AdModel adModel) {
        if (adModel == null) {
            return null;
        }

        Ad ad = new Ad();

        ad.setTitle(adModel.getTitle());
        ad.setPrice(adModel.getPrice());
        ad.setPk(adModel.getId());
        ad.setAuthor(adModel.getUserModel().getId());
        ad.setImage(ad.getImage());
        return ad;
    }

    public static ExtendedAd toExtendAd(AdModel adModel, UserModel userModel) {
        if (adModel == null) {
            return null;
        }

        ExtendedAd extendedAd = new ExtendedAd();

        extendedAd.setPk(adModel.getId());
        extendedAd.setPrice(adModel.getPrice());
        extendedAd.setTitle(adModel.getTitle());
        extendedAd.setDescription(adModel.getDescription());
        extendedAd.setAuthorFirstName(userModel.getFirstName());
        extendedAd.setAuthorLastName(userModel.getLastName());
        extendedAd.setPhone(userModel.getPhone());
        extendedAd.setEmail(userModel.getEmail());
        return extendedAd;
    }

    public static AdModel toAdModel(CreateOrUpdateAd ad) {
        if (ad == null) {
            return null;
        }
        AdModel adModel = new AdModel();

        adModel.setTitle(ad.getTitle());
        adModel.setDescription(ad.getDescription());
        adModel.setPrice(ad.getPrice());
        return adModel;
    }
}
