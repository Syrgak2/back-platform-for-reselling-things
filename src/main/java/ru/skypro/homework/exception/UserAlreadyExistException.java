package ru.skypro.homework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpStatusCodeException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserAlreadyExistException extends HttpStatusCodeException {
    public UserAlreadyExistException(){
        super(HttpStatus.BAD_REQUEST, "Такой пользователь уже существует, попробуйте другой email или номер");
    }
}
