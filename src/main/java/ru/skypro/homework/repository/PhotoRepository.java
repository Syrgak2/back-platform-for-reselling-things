package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.model.AdPhoto;

public interface PhotoRepository extends JpaRepository<AdPhoto, Long> {

}
