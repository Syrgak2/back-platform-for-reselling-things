package ru.skypro.homework.constants;

import org.springframework.mock.web.MockMultipartFile;
import ru.skypro.homework.dto.ads.CreateOrUpdateAdDTO;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Photo;
import ru.skypro.homework.model.User;

import java.io.IOException;
import java.util.List;

/**
 * класс для тестовых объектов
 */
public class Constants {
    private static final String fileName = "testFile.txt";
    private static final String contentType = "text/plain";
    private static final byte[] content = "This is the file content".getBytes();
    public static final MockMultipartFile MULTIPART_FILE = new MockMultipartFile(fileName, fileName, contentType, content);

    public final static Ad AD = new Ad(1L, "Test", "Test", 1000);
    public final static CreateOrUpdateAdDTO CREATE_OR_UPDATE_AD_DTO = new CreateOrUpdateAdDTO("Test", 1999,"Test" );
    public static final List<Ad> ADS = List.of(AD);

    public
    final static Photo PHOTO;

    static {
        try {
            PHOTO = new Photo(1L, MULTIPART_FILE.getSize(), MULTIPART_FILE.getContentType(), MULTIPART_FILE.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final User USER = new User(1L, "Test", "Test", "Test");
}
