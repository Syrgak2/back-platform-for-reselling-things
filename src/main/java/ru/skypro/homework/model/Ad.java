package ru.skypro.homework.model;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.util.Collection;

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
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "ad")
    private Collection<Comment> comments;
    @OneToOne
    @JoinColumn(name = "photo_id")
    private Photo image;

    public Ad() {
    }
}
