package ru.skypro.homework.service.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.auth.NewPasswordDTO;
import ru.skypro.homework.dto.user.UpdateUserDTO;
import ru.skypro.homework.exception.NotFoundException;
import ru.skypro.homework.model.Photo;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.PhotoService;
import ru.skypro.homework.service.UserService;

import java.io.IOException;

import static ru.skypro.homework.constant.Constants.URL_PHOTO_CONSTANT;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PhotoService photoService;
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserServiceImpl(UserRepository userRepository, PhotoService photoService) {
        this.userRepository = userRepository;
        this.photoService = photoService;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
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
    public User findByCommentId(Long id) {
        return userRepository.findByCommentId(id).orElse(null);
    }

    @Override
    public Boolean saveAvatar(MultipartFile file, String userName) throws IOException {
        User user = find(userName);
        if (user == null) {
            throw new NotFoundException();
        }

        if (user.getUserAvatar() != null) {
            Photo photo = user.getUserAvatar();
            user.setUserAvatar(null);
            userRepository.save(user);
            photoService.remove(photo.getId());

        }
        Photo savedPhoto = photoService.save(file);

        user.setUserAvatar(savedPhoto);
        user.setImageUrl(URL_PHOTO_CONSTANT + savedPhoto.getId());
        userRepository.save(user);

        return true;
    }

    @Override
    public User edite(String username, UpdateUserDTO updateUserDTO) {
        return userRepository.findByUsername(username)
                .map(foundUser -> {
                    foundUser.setFirstName(updateUserDTO.getFirstName());
                    foundUser.setLastName(updateUserDTO.getLastName());
                    foundUser.setPhone(updateUserDTO.getPhone());
                    return userRepository.save(foundUser);
                }).orElse(null);
    }

    @Override
    public boolean setPassword(String name, NewPasswordDTO newPassword) {
        User user = find(name);
        if (passwordEncoder.matches(newPassword.getCurrentPassword(), user.getPassword())) {
            user.setPassword(newPassword.getNewPassword());
            return true;
        }

        return false;
    }

}
