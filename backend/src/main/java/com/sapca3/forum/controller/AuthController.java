package com.sapca3.forum.controller;

import com.sapca3.forum.dto.LoginRequest;
import com.sapca3.forum.dto.RegisterRequest;
import com.sapca3.forum.dto.UserProfileDto;
import com.sapca3.forum.exception.UnauthorisedException;
import com.sapca3.forum.model.User;
import com.sapca3.forum.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController{
    public static final String SESSION_USER_ID = "userId";
    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserProfileDto> register(@Valid @RequestBody RegisterRequest request){
        User user = authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(UserProfileDto.from(user));
    }

    @PostMapping("/login")
    public UserProfileDto login(@Valid @RequestBody LoginRequest request, HttpServletRequest httpRequest){
        User user = authService.login(request);

        HttpSession oldSession = httpRequest.getSession(false);
        if(oldSession != null){
            oldSession.invalidate();
        }
        HttpSession session = httpRequest.getSession(true);
        session.setAttribute(SESSION_USER_ID, user.getId());

        return UserProfileDto.from(user);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest httpRequest){
        HttpSession session = httpRequest.getSession(false);
        if(session != null){
            session.invalidate();
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/me")
    public UserProfileDto me(HttpSession session){
        Long userId = (Long) session.getAttribute(SESSION_USER_ID);
        if(userId == null){
            throw new UnauthorisedException("Not logged in");
        }
        return UserProfileDto.from(authService.getById(userId));
    }


}