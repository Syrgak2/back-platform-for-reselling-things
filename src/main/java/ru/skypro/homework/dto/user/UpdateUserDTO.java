package ru.skypro.homework.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDTO {
    @Schema(description = "Имя пользователя")
    @Size(min = 3, max = 10,
            message = "ИМЯ ПОЛЬЗОВАТЕЛЯ ДОЛЖНО БЫТЬ В ДИАПАЗОНЕ ОТ 3 ДО 10 СИМВОЛОВ!")
    private String firstName;

    @Schema(description = "Фамилия пользователя")
    @Size(min = 3, max = 10,
            message = "ФАМИЛИЯ ПОЛЬЗОВАТЕЛЯ ДОЛЖНА БЫТЬ В ДИАПАЗОНЕ ОТ 3 ДО 10 СИМВОЛОВ!")
    private String lastName;

    @Schema(description = "Телефон пользователя")
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}",
            message = "ТЕЛЕФОН ПОЛЬЗОВАТЕЛЯ ДОЛЖЕН БЫТЬ В СООТВЕТСТВИИ С ШАБЛОНОМ +7 (777) 777-77-77!")
    private String phone;
}
