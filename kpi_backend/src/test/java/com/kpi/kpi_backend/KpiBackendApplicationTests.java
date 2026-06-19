package com.kpi.kpi_backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class KpiBackendApplicationTests {

	@Test
	void contextLoads() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println("HASHED_PASSWORD_OUTPUT: " + encoder.encode("Password"));
	}

}
