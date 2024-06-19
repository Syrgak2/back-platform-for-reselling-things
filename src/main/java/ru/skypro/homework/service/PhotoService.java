package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.Photo;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Path;

public interface PhotoService {
    Photo find(Long id);

    Photo save(MultipartFile file) throws IOException;

    byte[] getPhoto(Long id);

    Boolean remove(Long id);

}
