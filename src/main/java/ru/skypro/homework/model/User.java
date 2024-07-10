package ru.skypro.homework.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ru.skypro.homework.dto.Role;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;


@Entity
@Data
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", unique = true)
    @NotNull
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phone;

    private String imageUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @OneToOne
    @JoinColumn(name = "photo_id")
    private Photo userAvatar;

    @OneToMany(mappedBy = "user")
    private Collection<Ad> ads;

    @OneToMany(mappedBy = "user")
    private Collection<Comment> comment;

    public User() {
    }

    /**
     * Конструктор для создания течтовых обектов
     */
    public User(Long id, String username, String password, String firstName) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
    }
}
