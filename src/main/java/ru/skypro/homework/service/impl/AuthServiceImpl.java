package ru.skypro.homework.service.impl;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.auth.RegisterDTO;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.userDetails.CustomUserDetails;

@Service
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder encoder;
    private CustomUserDetails userDetails;

    public AuthServiceImpl(PasswordEncoder passwordEncoder,
                           CustomUserDetails userDetails) {
        this.userDetails = userDetails;
        this.encoder = passwordEncoder;
    }

    @Override
    public boolean login(String userName, String password) {
        userDetails.loadUserByUsername(userName);
        UserDetails userDetails = u
        return encoder.matches(password, userDetails.getPassword());
    }

    @Override
    public boolean register(RegisterDTO register) {
        if (manager.userExists(register.getUsername())) {
            return false;
        }
        manager.createUser(
                User.builder()
                        .passwordEncoder(this.encoder::encode)
                        .password(register.getPassword())
                        .username(register.getUsername())
                        .roles(register.getRole().name())
                        .build());
        return true;
    }

}
