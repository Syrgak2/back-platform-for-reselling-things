package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long> {
}
