package ru.skypro.homework.dto.ads;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO класс для добавления и обновление информации об объявление
 */
@Data
@Getter
@Setter
public class CreateOrUpdateAdDTO {
    private String title;
    private int price;
    private String description;

    public CreateOrUpdateAdDTO() {
    }
}
