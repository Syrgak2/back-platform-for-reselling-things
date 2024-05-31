package ru.skypro.homework.constants;

import ru.skypro.homework.dto.ads.Ad;
import ru.skypro.homework.dto.ads.Ads;
import ru.skypro.homework.dto.ads.CreateOrUpdateAd;
import ru.skypro.homework.dto.ads.ExtendedAd;

import java.util.ArrayList;
import java.util.List;

/**
 * класс для тестовых объектов
 */
public class Constants {
    public static String HOST = "http://localhost:";
    public static Ad AD = new Ad(1L, "Test", 1, 500, "Test");
    public static List<Ad> ADS_LIST = new ArrayList<>(List.of(AD));
    public static Ads ADS = new Ads(1, ADS_LIST);

    public static CreateOrUpdateAd CREATE_OR_UPDATE_AD = new CreateOrUpdateAd("Test", 500, "Test");

    public static ExtendedAd EXTENDED_AD = new ExtendedAd(1, "TEST", "Test", "Test", "Test");
}
