package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import ru.skypro.homework.dto.auth.RegisterDTO;
import ru.skypro.homework.exception.NotFoundException;
import ru.skypro.homework.exception.UserNameHasAlreadyAdded;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.service.UserService;
import ru.skypro.homework.userDetails.CustomUserDetails;

import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;

@Service
@Validated
@Transactional(isolation = SERIALIZABLE)
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder encoder;
    private final UserService userService;
    private final CustomUserDetails customUserDetails;
    private final UserMapper userMapper = UserMapper.INSTANCE;


    public AuthServiceImpl(PasswordEncoder passwordEncoder,
                           CustomUserDetails customUserDetails,
                           UserService userService) {
        this.customUserDetails = customUserDetails;
        this.encoder = passwordEncoder;
        this.userService = userService;
    }

    @Override
    public boolean login(String userName, String password) {
        UserDetails userDetails = customUserDetails.loadUserByUsername(userName);
        return encoder.matches(password, userDetails.getPassword());
    }

    @Override
    public boolean register(RegisterDTO register) {
        User user = userMapper.registerToUser(register);
        if (userService.find(register.getUsername()) != null) {
            throw new UserNameHasAlreadyAdded();
        }

        user.setPassword(encoder.encode(user.getPassword()));
        userService.save(user);
        return true;
    }

}
