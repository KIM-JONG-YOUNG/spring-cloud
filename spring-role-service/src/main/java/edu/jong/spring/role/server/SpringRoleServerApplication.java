package edu.jong.spring.role.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@SpringBootApplication
@ComponentScan(basePackages = "edu.jong.spring")
public class SpringRoleServerApplication {

	@Bean
	OpenAPI openAPI() {
		return new OpenAPI().info(new Info()
				.title("Role API Documents")
				.version("1.0.0"));
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringRoleServerApplication.class, args);
	}
}
