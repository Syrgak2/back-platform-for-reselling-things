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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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


    public Ad(Long id, String title, String description, int price) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
    }
}
