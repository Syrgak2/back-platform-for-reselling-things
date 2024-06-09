package ru.skypro.homework.service.entity_service;

import ru.skypro.homework.model.User;

import java.util.Collection;

public interface CheckService {
    boolean checkName(String name);

    String validatePhoneNumber(String phoneNumber);

    boolean checkPhoneNumber(String phoneNumber);

    boolean checkEmail(String email);

    boolean checkUserAlreadyExist(Collection<User> users, User user);
}
