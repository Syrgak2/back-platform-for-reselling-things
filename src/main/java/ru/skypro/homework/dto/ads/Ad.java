package ru.skypro.homework.dto.ads;

import lombok.Data;

/**
 * DTO класс для отправки объявлений.
 */
@Data
public class Ad {
    private Long author;
    private String image;
    private int pk;
    private int price;
    private String title;

    public Ad() {
    }
}
