package com.sapca3.forum.dto;

import com.sapca3.forum.model.User;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

public record UserProfileDto(
        Long id,
        String username,
        String role,
        String gamertag,
        String bio,
        LocalDateTime createdAt
){
    public static UserProfileDto from(User user){
        return new UserProfileDto(
                user.getId(),
                user.getUsername(),
                user.getRole().name(),
                user.getGamertag(),
                user.getBio(),
                user.getCreatedAt()
        );
    }
}