package ru.skypro.homework.dto.ads;

import lombok.Data;
import lombok.Getter;

/**
 * DTA для передачи всей информации про объявление
 */
@Data
@Getter
public class ExtendedAdDTO {
    private Long pk;
    private String authorFirstName;
    private String authorLastName;
    private String description;
    private String email;
    private String phone;
    private int price;
    private String title;

    public ExtendedAdDTO() {
    }
}
