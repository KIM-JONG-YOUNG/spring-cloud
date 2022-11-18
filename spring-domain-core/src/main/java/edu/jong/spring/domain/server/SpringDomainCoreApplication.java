package edu.jong.spring.domain.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "edu.jong.spring")
public class SpringDomainCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDomainCoreApplication.class, args);
	}
}
