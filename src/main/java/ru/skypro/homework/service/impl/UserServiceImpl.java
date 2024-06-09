package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.exception.NotFoundException;
import ru.skypro.homework.model.Photo;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.entity_service.CheckService;
import ru.skypro.homework.service.entity_service.PhotoService;
import ru.skypro.homework.service.entity_service.UserService;

import java.io.IOException;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PhotoService photoService;
    private final CheckService checkService;

    public UserServiceImpl(UserRepository userRepository, PhotoService photoService, CheckService checkService) {
        this.userRepository = userRepository;
        this.photoService = photoService;
        this.checkService = checkService;
    }


    @Override
    public User find(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User find(String userName) {
        return userRepository.findByUsername(userName).orElse(null);
    }

    @Override
    public Boolean saveAvatar(MultipartFile file, String userName) throws IOException {
        User user = find(userName);
        if (user == null) {
            throw new NotFoundException();
        }

        if (user.getUserAvatar() != null) {
            Photo photo = user.getUserAvatar();
            photoService.remove(photo.getId());

            user.setUserAvatar(null);
            userRepository.save(user);
        }
        Photo savedPhoto = photoService.save(file);

        user.setUserAvatar(savedPhoto);
        userRepository.save(user);

        return true;
    }

    @Override
    public User registerUser(String username,
                             String email,
                             String password,
                             String firstName,
                             String lastName,
                             String phone
    ) {
        checkService.checkName(firstName);
        checkService.checkName(lastName);
        checkService.checkPhoneNumber(phone);
        phone = checkService.validatePhoneNumber(phone);
        checkService.checkEmail(email);
        User user = new User(username, email, password, firstName,lastName,phone);
        checkService.checkUserAlreadyExist(userRepository.findAll(), user);
        return userRepository.save(user);
    }

}
