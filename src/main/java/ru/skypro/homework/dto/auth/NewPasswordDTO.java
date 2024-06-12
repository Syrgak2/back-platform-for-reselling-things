package ru.skypro.homework.dto.auth;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class NewPasswordDTO {
    private String currentPassword;
    private String newPassword;
}
