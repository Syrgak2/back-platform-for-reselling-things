package ru.skypro.homework.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
public class AdModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private int price;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel userModel;
    @OneToMany(mappedBy = "adModel")
    private Collection<CommentModel> commentModels;
    @OneToOne
    @JoinColumn(name = "photo_id")
    private Photo Photo;

    public AdModel() {
    }
}
