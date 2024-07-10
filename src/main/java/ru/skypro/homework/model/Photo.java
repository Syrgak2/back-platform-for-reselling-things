package ru.skypro.homework.model;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;

@Data
@Getter
@Entity
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long  fileSize;
    private String mediaType;
    @Lob
    private byte[] image;

    public Photo() {
    }

    public Photo(Long id, long fileSize, String mediaType, byte[] image) {
        this.id = id;
        this.fileSize = fileSize;
        this.mediaType = mediaType;
        this.image = image;
    }
}
