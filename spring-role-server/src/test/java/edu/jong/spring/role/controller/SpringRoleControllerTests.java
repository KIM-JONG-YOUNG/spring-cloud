package edu.jong.spring.role.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
public class SpringRoleControllerTests {

	@Test
	void contextLoad() {
		System.out.println("Context Load!");
	}
}
