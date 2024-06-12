package ru.skypro.homework.dto.auth;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class LoginDTO {

    private String username;
    private String password;
}
