package ru.skypro.homework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpStatusCodeException;
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalideEmailException extends HttpStatusCodeException {
    public InvalideEmailException() {
        super(HttpStatus.BAD_REQUEST, "email введен не корректно!");
    }
}
