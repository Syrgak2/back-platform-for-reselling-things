package ru.skypro.homework.dto.auth;

import lombok.Data;
import lombok.Getter;
import ru.skypro.homework.dto.Role;

@Data
@Getter
public class RegisterDTO {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private Role role;
}
