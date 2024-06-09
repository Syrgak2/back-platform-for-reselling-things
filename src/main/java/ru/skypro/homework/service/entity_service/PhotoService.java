package ru.skypro.homework.service.entity_service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.Photo;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Path;

public interface PhotoService {
    Photo save(MultipartFile file) throws IOException;

    void getPhoto(Path path, HttpServletResponse response) throws IOException;

    Boolean remove(Long id);

}
