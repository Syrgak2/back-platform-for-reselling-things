package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.model.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Long> {

}
