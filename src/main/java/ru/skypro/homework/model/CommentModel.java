package ru.skypro.homework.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class CommentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
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
