package ru.skypro.homework.model;

import lombok.Data;
import ru.skypro.homework.dto.Role;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity(name = "usersTable")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "phone")
    private String phone;
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

    public User(String username, String email, String password, String firstName, String lastName, String phone) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }
}
