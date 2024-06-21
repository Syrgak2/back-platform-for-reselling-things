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
@RequestMapping("/photo/")
public class PhotoController {

    private final PhotoService photoService;

    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping(value = "image/{imageId}")
    public ResponseEntity<byte[]> getPhoto(@PathVariable Long imageId) {
        Photo photo = photoService.find(imageId);
        if (photo == null) {
            return ResponseEntity.noContent().build();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(photo.getMediaType()));
        headers.setContentLength(photo.getImage().length);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(photo.getImage());
    }

}
