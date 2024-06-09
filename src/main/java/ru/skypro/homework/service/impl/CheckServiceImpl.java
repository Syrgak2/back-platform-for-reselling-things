package ru.skypro.homework.service.impl;


import org.springframework.stereotype.Service;
import ru.skypro.homework.exception.InvalideEmailException;
import ru.skypro.homework.exception.InvalideInputException;
import ru.skypro.homework.exception.InvalideNumberException;
import ru.skypro.homework.exception.UserAlreadyExistException;
import ru.skypro.homework.model.User;
import ru.skypro.homework.service.entity_service.CheckService;

import java.util.Collection;

@Service
public class CheckServiceImpl implements CheckService {
    @Override
    public boolean checkName(String name) {
        if (name == null
                || name.isBlank()
                || !name.matches("[а-яА-Я -]+")) {
            throw new InvalideInputException();
        }
        return true;
    }
    @Override
    public String validatePhoneNumber(String phoneNumber) {
        String first = phoneNumber.substring(0, 1).replace(phoneNumber.charAt(0), '7');
        String second = phoneNumber.substring(1, 4);
        String third = phoneNumber.substring(4, 7);
        String fourth = phoneNumber.substring(7, 9);
        String fives = phoneNumber.substring(9);
        return String.format("+%s-%s-%s-%s-%s", first, second, third, fourth, fives);
    }

    @Override
    public boolean checkPhoneNumber(String phoneNumber) {
        if (phoneNumber == null
                || phoneNumber.isBlank()
                || !phoneNumber.matches("[0-9]{11}")) {
            throw new InvalideNumberException();
        }
        return true;
    }
    @Override
    public boolean checkEmail(String email){
        if(email== null
                || email.isBlank()
                || !email.matches("[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+\\.[a-zA-Z0-9_-]+")){
            throw new InvalideEmailException();
        }
        return true;
    }
    @Override
    public boolean checkUserAlreadyExist(Collection<User> users, User user){
        for(User element: users){
            if(element.getEmail().equalsIgnoreCase(user.getEmail())){
                throw new UserAlreadyExistException();
            }
            if (element.getPhone().equalsIgnoreCase(user.getPhone())){
                throw new UserAlreadyExistException();
            }
        }
        return true;
    }
}
