package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.auth.NewPasswordDTO;
import ru.skypro.homework.dto.user.UpdateUserDTO;
import ru.skypro.homework.model.User;

import java.io.IOException;

public interface UserService {
    User find(Long id);


    User find(String userName);

    User findByCommentId(Long id);

    Boolean saveAvatar(MultipartFile file, String userName) throws IOException;

    User save(User user);

    User edite(Long userid, UpdateUserDTO updateUserDTO);

    boolean setPassword(String name, NewPasswordDTO newPassword);
}
