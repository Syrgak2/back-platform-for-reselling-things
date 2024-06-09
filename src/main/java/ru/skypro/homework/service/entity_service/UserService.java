package ru.skypro.homework.service.entity_service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.User;

import java.io.IOException;

public interface UserService {


    User find(Long id);


    User find(String userName);

    Boolean saveAvatar(MultipartFile file, String userName) throws IOException;

    User registerUser(String username,
                      String email,
                      String password,
                      String firstName,
                      String lastName,
                      String phone
    );
}
