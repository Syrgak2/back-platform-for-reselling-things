package ru.skypro.homework.model;

import lombok.Data;
import org.springframework.security.core.userdetails.User;
import ru.skypro.homework.dto.Role;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
public class UserModel {
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
    @JoinColumn(name = "user_avatar_id")
    private UserAvatar userAvatar;

    @OneToMany(mappedBy = "userModel")
    private Collection<AdModel> adModels;

    @OneToMany(mappedBy = "userModel")
    private Collection<Comment> comment;

    public UserModel() {
    }
}
