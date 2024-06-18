package ru.skypro.homework.exception;

public class BadFileException extends RuntimeException {
    public BadFileException() {
    }

    public BadFileException(String message) {
        super(message);
    }
}
