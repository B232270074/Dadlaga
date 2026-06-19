package com.kpi.kpi_backend.config;

import com.kpi.kpi_backend.entity.User;
import com.kpi.kpi_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @EventListener(ApplicationReadyEvent.class)
    public void loadUsers() {
        // admin хэрэглэгч байхгүй бол үүсгэх
        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPasswordHash(passwordEncoder.encode("admin"));
            admin.setRole("ADMIN");
            admin.setEmployeeId(1L);
            userRepository.save(admin);
            System.out.println("✅ User created: admin / admin");
        }
        
        // test хэрэглэгч байхгүй бол үүсгэх
        if (userRepository.findByUsername("test").isEmpty()) {
            User test = new User();
            test.setUsername("test");
            test.setPasswordHash(passwordEncoder.encode("Password"));
            test.setRole("USER");
            test.setEmployeeId(2L);
            userRepository.save(test);
            System.out.println("✅ User created: test / Password");
        }
    }
}
