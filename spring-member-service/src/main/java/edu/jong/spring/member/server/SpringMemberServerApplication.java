package edu.jong.spring.member.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "edu.jong.spring")
public class SpringMemberServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMemberServerApplication.class, args);
	}
}
