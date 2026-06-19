package com.kpi.kpi_backend.controller;

import com.kpi.kpi_backend.entity.User;
import com.kpi.kpi_backend.repository.UserRepository;
import com.kpi.kpi_backend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
        try {
            String username = loginRequest.get("username");
            String password = loginRequest.get("password");
            Map<String, Object> response = authService.login(username, password);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/test-password")
    public ResponseEntity<?> testPassword(@RequestParam String password) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String hash = "$2a$10$pOw0EeGztRkPn4sWqOFtFOEoCOzUYwVo2yCPgCQY1sKzYpZJk2HpS";
        boolean matches = encoder.matches(password, hash);
        return ResponseEntity.ok(Map.of(
            "matches", matches,
            "password", password,
            "hash", hash
        ));
    }

    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");
        
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String hash = encoder.encode(password);
        
        User user = new User();
        user.setUsername(username);
        user.setPasswordHash(hash);
        user.setRole("USER");
        user.setEmployeeId(1L);
        user.setCreatedAt(LocalDateTime.now());
        
        userRepository.save(user);
        
        return ResponseEntity.ok(Map.of(
            "username", username,
            "hash", hash,
            "message", "User created successfully! Use this password to login."
        ));
    }
}
