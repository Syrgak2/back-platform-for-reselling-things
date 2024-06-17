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
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class PhotoServiceImpl implements PhotoService {

    private final PhotoRepository photoRepository;
    Logger logger = LoggerFactory.getLogger(PhotoServiceImpl.class);

    public PhotoServiceImpl(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
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
        photo.setImage(generateImagePreview(file));

        return photoRepository.save(photo);

    }



    /**
     * Получает файл от диска по переданному пути и отправляет HttpResponse
     * Нужно доработать в зависемости от требований RestApi
     * @param path Путь, где сохранен файл
     * @param response HttpServletResponse для отправления на клиент
     * @throws IOException может выбросить ошибку
     */
    @Override
    public void getPhoto(Path path, HttpServletResponse response) throws IOException {
        logger.trace("Wos invoked method for get avatar");


        try (
                InputStream is = Files.newInputStream(path);
                OutputStream os = response.getOutputStream();
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            response.setStatus(200);
            bis.transferTo(os);
        }
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

    private byte[] generateImagePreview(MultipartFile file)throws IOException {
        if (file == null) {
            logger.warn("filePath is null");
            throw new NotFoundException("Файл не найден");
        }

        try (
                InputStream is = file.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                ByteArrayOutputStream baos = new ByteArrayOutputStream()
        ) {
            BufferedImage image = ImageIO.read(bis);

            int height = image.getHeight() / (image.getWidth() / 100);
            BufferedImage preview = new BufferedImage(100, height, image.getType());
            Graphics2D graphics = preview.createGraphics();
            graphics.drawImage(image, 0, 0, 100, height, null);
            graphics.dispose();

            ImageIO.write(preview, getExtension(file.getName()), baos);
            return baos.toByteArray();
        }
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
