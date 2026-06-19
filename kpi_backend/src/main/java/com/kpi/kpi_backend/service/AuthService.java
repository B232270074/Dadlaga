package com.kpi.kpi_backend.service;

import com.kpi.kpi_backend.entity.User;
import com.kpi.kpi_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public Map<String, Object> login(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Хэрэглэгч олдсонгүй"));

        String dbPassword = user.getPasswordHash();
        boolean isMatch = false;
        
        if (dbPassword != null && dbPassword.startsWith("$2a$")) {
            isMatch = passwordEncoder.matches(password, dbPassword);
        } else {
            // Fallback for manually inserted plaintext passwords in the database
            isMatch = password.equals(dbPassword);
        }

        if (!isMatch) {
            throw new RuntimeException("Нууц үг буруу байна");
        }

        String token = jwtService.generateToken(user.getUsername(), user.getId(), user.getRole());
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("username", user.getUsername());
        response.put("role", user.getRole());
        response.put("employeeId", user.getEmployeeId());

        return response;
    }
}
