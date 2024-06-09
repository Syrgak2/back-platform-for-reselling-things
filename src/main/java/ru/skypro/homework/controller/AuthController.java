package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.dto.Login;
import ru.skypro.homework.dto.user.Register;
import ru.skypro.homework.model.User;
import ru.skypro.homework.service.entity_service.AuthService;
import ru.skypro.homework.service.entity_service.UserService;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class AuthController {
    @Autowired
    private final UserService userService;
    private final AuthService authService;

    @Operation(
            summary = "Авторизация пользователя",
            tags = "Авторизация"
    )
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Login login) {
        if (authService.login(login.getUsername(), login.getPassword())) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @Operation(
            summary = "Регистрация пользователя",
            tags = "Регистрация"
    )

    @PostMapping("/register")
    public User register(String username,
                         String email,
                         String password,
                         String firstName,
                         String lastName,
                         String phone) {
        return userService.registerUser(username, email, password, firstName, lastName, phone);
    }
}
