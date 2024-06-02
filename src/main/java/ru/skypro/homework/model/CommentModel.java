package ru.skypro.homework.model;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
public class CommentModel {
    @Id
    @GeneratedValue
    private long id;
    private String text;
    private LocalDateTime messageDateTime;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel userModel;
    @ManyToOne
    @JoinColumn(name = "advertisement_id")
    private AdModel adModel;

    public CommentModel() {
    }
}
