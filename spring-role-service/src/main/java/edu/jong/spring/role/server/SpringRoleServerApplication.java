package edu.jong.spring.role.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "edu.jong.spring")
public class SpringRoleServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRoleServerApplication.class, args);
	}
}
