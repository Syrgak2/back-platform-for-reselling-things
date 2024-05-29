package ru.skypro.homework.model;

import ru.skypro.homework.dto.Role;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;

public class User {
    @Id
    @GeneratedValue
    private long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private Role role;

    private UserAvatar userAvatar;
    @OneToMany(mappedBy = "advertisement")
    private Collection<Advertisement> advertisements;
    @OneToMany(mappedBy = "comment")
    private Collection<Comment> comments;
}
