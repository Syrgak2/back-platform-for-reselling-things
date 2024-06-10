package ru.skypro.homework.model;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;

@Data
@Getter
@Entity
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private long  fileSize;
    private String mediaType;
    private String url;
    @Lob
    private byte[] image;

    public Photo() {
    }

}
