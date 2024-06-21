package ru.skypro.homework.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.exception.BadFileException;
import ru.skypro.homework.exception.NotFoundException;
import ru.skypro.homework.model.Photo;
import ru.skypro.homework.repository.PhotoRepository;
import ru.skypro.homework.service.PhotoService;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Objects;

@Service
public class PhotoServiceImpl implements PhotoService {

    private final PhotoRepository photoRepository;
    Logger logger = LoggerFactory.getLogger(PhotoServiceImpl.class);

    public PhotoServiceImpl(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    @Override
    public Photo find(Long id) {
        return photoRepository.findById(id).orElse(null);
    }

    /**
     * Метод для сохранения фото на диск по указанному пути
     * @param file файл которую нужно сохранить
     * @return полный путь файл
     * @throws IOException может выбросит ошибку
     */
    @Override
    public Photo save(MultipartFile file) throws IOException {
        logger.info("Wos invoked uploadPhoto");
        if (file == null) {
            logger.info("file is empty");
            throw new BadFileException("File is empty");
        }

        Photo photo = new Photo();
        photo.setFileSize(file.getSize());
        photo.setMediaType(file.getContentType());
        photo.setImage(file.getBytes());

        return photoRepository.save(photo);

    }



    /**
     * Получает файл от диска по переданному пути и отправляет HttpResponse
     * Нужно доработать в зависемости от требований RestApi
     * @param id id photo
     * @throws IOException может выбросить ошибку
     */
    @Override
    public byte[] getPhoto(Long id) {
        logger.trace("Wos invoked method for get avatar");

        Photo photo = find(id);

        if (photo == null) {
            throw new NotFoundException("File is not found");
        }

        return photo.getImage();
    }

    /**
     * Удаляет фото на диске по переданному пути
     * @param id id фото
     * @return если удалено true, иначе false
     */
    @Override
    public Boolean remove(Long id) {
        logger.trace("Wos invoked method for remove avatar");
        photoRepository.deleteById(id);
        return true;
    }

    /**
     * Сжимает файл для сохранения в базу
     * @param file путь файл для сжатия
     * @return byte код файла для сохранения на БД
     * @throws IOException может выбросить ошибку
     */
    private byte[] generateImagePreview(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            logger.warn("Uploaded file is null or empty");
            throw new NotFoundException("Файл не найден");
        }

        try (
                InputStream is = file.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(is);
                ByteArrayOutputStream baos = new ByteArrayOutputStream()
        ) {
            BufferedImage image = ImageIO.read(bis);
            if (image == null) {
                logger.warn("Could not read image from uploaded file");
                throw new IOException("Не удалось прочитать изображение из файла");
            }

            int width = 100;
            int height = (int) (((double) image.getHeight() / image.getWidth()) * width);
            BufferedImage preview = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics = preview.createGraphics();
            graphics.drawImage(image, 0, 0, width, height, null);
            graphics.dispose();

            String formatName = getExtension(Objects.requireNonNull(file.getOriginalFilename()));
            ImageIO.write(preview, formatName, baos);
            return baos.toByteArray();
        }
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
