package ru.skypro.homework.model;

import lombok.Data;
import lombok.Getter;
import ru.skypro.homework.dto.Role;

import javax.persistence.*;
import java.util.Collection;

@Data
@Getter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
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
}
