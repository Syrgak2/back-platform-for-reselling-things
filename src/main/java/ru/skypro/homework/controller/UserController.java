package ru.skypro.homework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.auth.NewPasswordDTO;
import ru.skypro.homework.dto.user.UpdateUserDTO;
import ru.skypro.homework.dto.user.UserDTO;
import ru.skypro.homework.exception.NotFoundException;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.User;
import ru.skypro.homework.service.UserService;

import java.io.IOException;

@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    private final UserMapper userMapper = UserMapper.INSTANCE;

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
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserDTO> getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = userService.find(authentication.getName());
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


        return ResponseEntity.ok(userMapper.usertoUserDto(user));
    }


    @PatchMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UpdateUserDTO> updateUser(@RequestBody UpdateUserDTO updateUser) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = userService.edite(authentication.getName(), updateUser);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(updateUser);
    }

    /**
     * Нужно проверить работу после создания метода сохронения пользователей
     */
    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> updateUserImage(@RequestParam MultipartFile image) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        try {
            userService.saveAvatar(image, userName);
            return ResponseEntity.ok().build();
        } catch (IOException ioException) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
