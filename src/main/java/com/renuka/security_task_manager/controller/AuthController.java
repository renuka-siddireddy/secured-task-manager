package com.renuka.security_task_manager.controller;

import com.renuka.security_task_manager.dto.AuthRequest;
import com.renuka.security_task_manager.dto.AuthResponse;
import com.renuka.security_task_manager.model.User;
import com.renuka.security_task_manager.security.JwtUtil;
import com.renuka.security_task_manager.service.UserService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserService service;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder encoder;

    public AuthController(UserService service,
                          JwtUtil jwtUtil,
                          PasswordEncoder encoder) {
        this.service = service;
        this.jwtUtil = jwtUtil;
        this.encoder = encoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody AuthRequest request) {

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setRole("ROLE_USER");

        service.register(user);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthRequest request) {

        User user = service.findByUsername(request.getUsername());

        if (user == null || !encoder.matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }

        String token = jwtUtil.generateToken(
                user.getUsername(),
                user.getRole()
        );

        return ResponseEntity.ok(new AuthResponse(token));
    }
}