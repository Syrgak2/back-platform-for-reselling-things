package ru.skypro.homework.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class AdPhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String filePath;
    private long  fileSize;
    private String mediaType;
    private byte image;

    public AdPhoto() {
    }

}
