package ru.skypro.homework.mapper;

import ru.skypro.homework.dto.user.Register;
import ru.skypro.homework.dto.user.UpdateUser;
import ru.skypro.homework.dto.user.UserDTO;
import ru.skypro.homework.model.User;

public class UserMapper {


    public static UserDTO UsertoUserDto(User userModel) {
        if (userModel == null) {
            return null;
        }

        UserDTO user = new UserDTO();

        user.setId(userModel.getId());
        user.setEmail(user.getEmail());
        user.setFirstName(userModel.getFirstName());
        user.setLastName(userModel.getLastName());
        user.setPhone(userModel.getPhone());
        user.setImage(userModel.getUserAvatar().getUrl());
        user.setRole(userModel.getRole());
        return user;
    }

    public static User updateUserToUser(UpdateUser user) {
        if (user == null) {
            return null;
        }

        User userModel = new User();

        userModel.setFirstName(userModel.getFirstName());
        userModel.setLastName(user.getLastName());
        userModel.setPhone(user.getPhone());
        return userModel;
    }

    public static User registerToUser(Register register) {
        if (register == null) {
            return null;
        }

        User user = new User();

        user.setUsername(register.getUsername());
        user.setPassword(register.getPassword());
        user.setFirstName(register.getFirstName());
        user.setLastName(register.getLastName());
        user.setPhone(register.getPhone());
        user.setRole(register.getRole());
        return user;
    }

}
