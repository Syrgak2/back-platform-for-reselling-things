package ru.skypro.homework.service.impl;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.auth.RegisterDTO;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.userDetails.CustomUserDetails;

@Service
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder encoder;
    private CustomUserDetails customUserDetails;

    public AuthServiceImpl(PasswordEncoder passwordEncoder,
                           CustomUserDetails customUserDetails) {
        this.customUserDetails = customUserDetails;
        this.encoder = passwordEncoder;
    }

    @Override
    public boolean login(String userName, String password) {
        UserDetails userDetails = customUserDetails.loadUserByUsername(userName);
        return encoder.matches(password, userDetails.getPassword());
    }

    @Override
    public boolean register(RegisterDTO register) {
        return true;
    }

}
