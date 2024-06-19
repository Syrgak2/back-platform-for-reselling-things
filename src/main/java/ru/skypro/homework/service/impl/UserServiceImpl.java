package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.user.UpdateUserDTO;
import ru.skypro.homework.exception.NotFoundException;
import ru.skypro.homework.model.Photo;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.PhotoService;
import ru.skypro.homework.service.UserService;

import java.io.IOException;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PhotoService photoService;

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
    public User edite(Long userid, UpdateUserDTO updateUserDTO) {
        return userRepository.findById(userid)
                .map(foundUser -> {
                    foundUser.setFirstName(updateUserDTO.getFirstName());
                    foundUser.setLastName(updateUserDTO.getLastName());
                    foundUser.setPhone(updateUserDTO.getPhone());
                    return userRepository.save(foundUser);
                }).orElse(null);
    }

}
