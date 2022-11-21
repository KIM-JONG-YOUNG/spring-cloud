package edu.jong.spring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import edu.jong.spring.redis.config.RedisProperties;

@SpringBootTest
@ActiveProfiles("test")
@SpringBootApplication
public class RedisServerTestApplication {

	@Autowired
	RedisProperties properties;
	
	@Test
	void contextLoad() {
		System.out.println(properties);
	}
}
