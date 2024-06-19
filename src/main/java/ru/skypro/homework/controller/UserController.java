package ru.skypro.homework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.auth.NewPasswordDTO;
import ru.skypro.homework.dto.user.UpdateUserDTO;
import ru.skypro.homework.dto.user.UserDTO;
import ru.skypro.homework.exception.NotFoundException;
import ru.skypro.homework.service.UserService;

import java.io.IOException;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/set_password")
    public ResponseEntity<?> setPassword(@RequestBody NewPasswordDTO newPassword) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.isAuthenticated()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        if (!userService.setPassword(authentication.getName(), newPassword)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        return ResponseEntity.ok().build();
    }
    @GetMapping("/me")
    public ResponseEntity<?> getUser() {
        return ResponseEntity.ok(new UserDTO());
    }


    @PatchMapping("/me")
    public ResponseEntity<?> updateUser(@RequestBody UpdateUserDTO updateUser) {
        return ResponseEntity.ok(new UpdateUserDTO());
    }

    /**
     * Нужно проверить работу после создания метода сохронения пользователей
     */
    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateUserImage(@RequestParam MultipartFile image) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        if (userName == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        try {
            userService.saveAvatar(image, userName);
            return ResponseEntity.ok().build();
        } catch (IOException ioException) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NotFoundException notFoundException) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

}
