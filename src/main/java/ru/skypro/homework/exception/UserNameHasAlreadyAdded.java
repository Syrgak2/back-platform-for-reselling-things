package ru.skypro.homework.exception;

public class UserNameHasAlreadyAdded extends RuntimeException {
    public UserNameHasAlreadyAdded() {
    }

    public UserNameHasAlreadyAdded(String message) {
        super(message);
    }
}
