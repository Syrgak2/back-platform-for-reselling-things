package ru.skypro.homework.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Data
public class Photo {
    @Id
    @GeneratedValue
    private long id;
    private String filePath;
    private long  fileSize;
    private String mediaType;
    private byte image;
    @OneToOne
    @JoinColumn(name = "user_id")
    private UserModel userModel;

    public Photo() {
    }

}
