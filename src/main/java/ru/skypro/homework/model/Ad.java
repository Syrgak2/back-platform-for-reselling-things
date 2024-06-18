package ru.skypro.homework.model;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Data
@Getter
@Entity
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String description;
    private int price;
    private String imageUrl;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "ad")
    private List<Comment> comments;
    @OneToOne
    @JoinColumn(name = "photo_id")
    private Photo image;

    public Ad() {
    }
}
