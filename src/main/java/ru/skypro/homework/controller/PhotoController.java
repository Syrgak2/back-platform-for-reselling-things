package ru.skypro.homework.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.model.Photo;
import ru.skypro.homework.service.PhotoService;

@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/images")
public class PhotoController {

    private final PhotoService photoService;

    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping
    public ResponseEntity<byte[]> getPhoto(@RequestParam Long imageId) {
        Photo photo = photoService.find(imageId);
        if (photo == null) {
            return ResponseEntity.noContent().build();
        }

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.parseMediaType(photo.getMediaType()));
        httpHeaders.setContentLength(photo.getImage().length);

        return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(photo.getImage());
    }
}
