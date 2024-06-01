package ru.skypro.homework.dto.ads;

import lombok.Data;

/**
 * DTA для передачи всей информации про объявление
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
}
