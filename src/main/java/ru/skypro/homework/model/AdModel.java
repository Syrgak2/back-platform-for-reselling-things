package ru.skypro.homework.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Data
public class AdModel {
    @Id
    @GeneratedValue
    private long id;
    private String title;
    private String description;
    private int price;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel userModel;
    @OneToMany(mappedBy = "advertisement")
    private Collection<CommentModel> commentModels;
    @OneToOne
    @JoinColumn(name = "photo_id")
    private Photo Photo;

    public AdModel() {
    }
}
