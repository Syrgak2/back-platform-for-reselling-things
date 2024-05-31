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

    /**
     *Конструктор для создания тестовых объектов
     */
    public Ad(Long author, String image, int pk, int price, String title) {
        this.author = author;
        this.image = image;
        this.pk = pk;
        this.price = price;
        this.title = title;
    }
}
