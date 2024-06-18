package ru.skypro.homework.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skypro.homework.dto.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @Schema(description = "ID пользователя")
    private Integer id;

    @Schema(description = "Логин пользователя")
    private String email;

    @Schema(description = "Имя пользователя")
    private String firstName;

    @Schema(description = "Фамилия пользователя")
    private String lastName;

    @Schema(description = "Телефон пользователя")
    private String phone;

    @Schema(description = "Ссылка на аватар пользователя")
    private String image;

    @Schema(description = "Роль пользователя")
    private Role role;
}
