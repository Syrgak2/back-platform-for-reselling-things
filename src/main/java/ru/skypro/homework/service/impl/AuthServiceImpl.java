package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import ru.skypro.homework.dto.auth.RegisterDTO;
import ru.skypro.homework.exception.NotFoundException;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.userDetails.CustomUserDetails;

import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;

@Service
@Validated
@RequiredArgsConstructor
@Transactional(isolation = SERIALIZABLE)
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder encoder;
    private CustomUserDetails customUserDetails;

    public AuthServiceImpl(PasswordEncoder passwordEncoder,
                           CustomUserDetails customUserDetails) {
        this.customUserDetails = customUserDetails;
        this.encoder = passwordEncoder;
    }
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public boolean login(String userName, String password) {
        UserDetails userDetails = customUserDetails.loadUserByUsername(userName);
        return encoder.matches(password, userDetails.getPassword());
    }

    @Override
    public boolean register(RegisterDTO register) {
        User user = userMapper.registerToUser(register);
        if(userRepository.findByUsername(user.getUsername()).isPresent()){
            throw new NotFoundException();
        }

        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

}
