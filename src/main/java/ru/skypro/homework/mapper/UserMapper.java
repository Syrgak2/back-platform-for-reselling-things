package ru.skypro.homework.mapper;

import ru.skypro.homework.dto.user.User;
import ru.skypro.homework.model.UserModel;

public class UserMapper {


    public static User toUserDto(UserModel userModel) {
        if (userModel == null) {
            return null;
        }

        User user = new User();

        user.setId((int) userModel.getId());
        user.setEmail(user.getEmail());
        user.setFirstName(userModel.getFirstName());
        user.setLastName(userModel.getLastName());
        user.setPhone(userModel.getPhone());
        user.setImage(String.valueOf(userModel.getPhoto().getImage()));
        user.setRole(userModel.getRole());
        return user;
    }

}
