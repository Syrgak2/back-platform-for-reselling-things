package ru.skypro.homework.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class CommentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long author;
    private String authorImage;
    private String authorFirstName;
    private LocalDateTime createdAt;
    private Long pk;
    private String text;

    @ManyToOne
    @JoinColumn(name = "ad_id")
    private AdModel adModel;

    @ManyToOne
    @JoinColumn(name = "uaer_id")
    private UserModel userModel;

    public CommentModel() {

    }
}
