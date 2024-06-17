package ru.skypro.homework.dto.ads;

import lombok.Data;
import lombok.Getter;

/**
 * DTO класс для отправки объявлений.
 */
@Data
@Getter
public class AdDTO {
    private Long author;
    private String image;
    private Long pk;
    private int price;
    private String title;

    public AdDTO() {
    }
}
