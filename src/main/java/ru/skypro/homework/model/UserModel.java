package ru.skypro.homework.model;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.skypro.homework.dto.Role;

import javax.persistence.*;
import java.util.Collection;

@Data
public class UserModel {
    @Id
    @GeneratedValue
    private long id;
    private String username;
    private String email;
    private PasswordEncoder passwordEncoder;
    private String firstName;
    private String lastName;
    private String phone;
    private Role role;

    @OneToOne
    @JoinColumn(name = "user_avatar_id")
    private Photo photo;
    @OneToMany(mappedBy = "advertisement")
    private Collection<AdModel> adModels;
    @OneToMany(mappedBy = "comment")
    private Collection<CommentModel> commentModels;

    public UserModel() {
    }
}
