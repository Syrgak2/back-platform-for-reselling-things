package ru.skypro.homework.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.exception.NotFoundException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

public class PhotoService {

    Logger logger = LoggerFactory.getLogger(PhotoService.class);

    /**
     * Метод для сохранения фото на диск по указанному пути
     * @param dir путь где куда нужно сохранить фото
     * @param id id объекта к которуму прявязан фото
     * @param file файл которую нужно сохранить
     * @return полный путь файл
     * @throws IOException может выбросит ошибку
     */
    public Path uploadPhoto(String dir, Long id, MultipartFile file) throws IOException {
        logger.info("Wos invoked uploadPhoto");
        Path filePath = Path.of(dir, id + "." + getExtension(Objects.requireNonNull(file.getOriginalFilename())));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        try (
                InputStream is = file.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }
        return filePath;
    }

    /**
     * Сжимает файл для сохранения в базу
     * @param filePath путь файле откуда можно взять для сжатия
     * @return byte код файла для сохранения на БД
     * @throws IOException может выбросить ошибку
     */
    public byte[] generateImagePreview(Path filePath) throws IOException {
        if (filePath == null) {
            logger.warn("filePath is null");
            throw new NotFoundException("Файл не найден");
        }

        try (
                InputStream is = Files.newInputStream(filePath);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                ByteArrayOutputStream baos = new ByteArrayOutputStream()
        ) {
            BufferedImage image = ImageIO.read(bis);

            int height = image.getHeight() / (image.getWidth() / 100);
            BufferedImage preview = new BufferedImage(100, height, image.getType());
            Graphics2D graphics = preview.createGraphics();
            graphics.drawImage(image, 0, 0, 100, height, null);
            graphics.dispose();

            ImageIO.write(preview, getExtension(filePath.getFileName().toString()), baos);
            return baos.toByteArray();
        }
    }

    /**
     * Получает файл от диска по переданному пути и отправляет HttpResponse
     * Нужно доработать в зависемости от требований RestApi
     * @param path Путь, где сохранен файл
     * @param response HttpServletResponse для отправления на клиент
     * @throws IOException может выбросить ошибку
     */
    public void getAvatar(Path path, HttpServletResponse response) throws IOException {
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
     * @param path путь где сохранен файл
     * @return если удалено true, иначе false
     */
    public Boolean removeAvatar(Path path) {
        logger.trace("Wos invoked method for remove avatar");
        if (path == null) {
            logger.warn("Path is null");
            throw new NotFoundException("Файл не найден");
        }
        try {
            Files.delete(path);
            return true;
        } catch (IOException ignored) {
            return false;
        }
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
