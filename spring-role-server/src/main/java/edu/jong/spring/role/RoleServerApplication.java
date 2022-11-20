package edu.jong.spring.role;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@EnableFeignClients(basePackages = "edu.jong.spring")
@SpringBootApplication(scanBasePackages = "edu.jong.spring")
public class RoleServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoleServerApplication.class, args);
	}
}
 