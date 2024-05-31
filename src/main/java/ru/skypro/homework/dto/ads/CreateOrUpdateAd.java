package ru.skypro.homework.dto.ads;

import lombok.Data;

/**
 * DTO класс для добавления и обновление информации об объявление
 */
@Data
public class CreateOrUpdateAd {
    private String title;
    private int price;
    private String description;

    public CreateOrUpdateAd() {
    }
}
