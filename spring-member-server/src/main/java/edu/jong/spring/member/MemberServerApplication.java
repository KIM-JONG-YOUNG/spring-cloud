package edu.jong.spring.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import edu.jong.spring.common.constants.PackagePaths;

@EnableDiscoveryClient
@EnableFeignClients(basePackages = PackagePaths.ROOT_PATH)
@SpringBootApplication(scanBasePackages = PackagePaths.ROOT_PATH)
public class MemberServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MemberServerApplication.class, args);
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
