package ru.skypro.homework.mapper;

import ru.skypro.homework.dto.user.UserDTO;
import ru.skypro.homework.model.User;

public class UserMapper {


    public static UserDTO toUserDto(User userModel) {
        if (userModel == null) {
            return null;
        }

        UserDTO user = new UserDTO();

        user.setId(userModel.getId());
        user.setEmail(user.getEmail());
        user.setFirstName(userModel.getFirstName());
        user.setLastName(userModel.getLastName());
        user.setPhone(userModel.getPhone());
        user.setImage(String.valueOf(userModel.getUserAvatar().getImage()));
        user.setRole(userModel.getRole());
        return user;
    }

}
