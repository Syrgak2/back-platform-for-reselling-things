package ru.skypro.homework.dto.ads;

import lombok.Data;

/**
 * DTA для передачи всю информации про объявление
 */
@Data
public class ExtendedAd {
    private int pk;
    private String authorFirstName;
    private String authorLastName;
    private String description;
    private String email;
    private String phone;
    private int price;
    private String title;

    public ExtendedAd() {
    }

    /**
     *Конструктор для создания тестовых объектов
     */
    public ExtendedAd(int pk, String authorFirstName, String authorLastName, String description, String title) {
        this.pk = pk;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
        this.description = description;
        this.title = title;
    }
}
