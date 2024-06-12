package ru.skypro.homework.dto.ads;

import lombok.Data;
import lombok.Getter;

/**
 * DTO класс для добавления и обновление информации об объявление
 */
@Data
@Getter
public class CreateOrUpdateAdDTO {
    private String title;
    private int price;
    private String description;

    public CreateOrUpdateAdDTO() {
    }
}
