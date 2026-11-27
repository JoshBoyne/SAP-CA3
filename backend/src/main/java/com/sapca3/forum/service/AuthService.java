package com.sapca3.forum.service;

import com.sapca3.forum.dto.LoginRequest;
import com.sapca3.forum.dto.RegisterRequest;
import com.sapca3.forum.exception.ForbiddenException;
import com.sapca3.forum.exception.UnauthorisedException;
import com.sapca3.forum.model.Role;
import com.sapca3.forum.model.User;
import com.sapca3.forum.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.username())) {
            throw new IllegalArgumentException("Username is already taken");
        }
        if (userRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("Email is already registered");
        }

        User user = new User();
        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setPassword(request.password());
        user.setGamertag(request.gamertag());
        user.setRole(Role.MEMBER);

        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User login(LoginRequest request) {
        User user = userRepository.findByUsername(request.username())
                .orElseThrow(() -> new UnauthorisedException("Invalid username or password"));

        if (!user.getPassword().equals(request.password())) {
            throw new UnauthorisedException("Invalid username or password");
        }

        if (user.isBanned()) {
            throw new ForbiddenException("This account has been banned");
        }

        return user;
    }

    @Transactional(readOnly = true)
    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UnauthorisedException("Not logged in"));
    }
}