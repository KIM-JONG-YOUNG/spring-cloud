package edu.jong.spring.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = "edu.jong.spring")
@EnableFeignClients
public class MemberServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MemberServerApplication.class, args);
	}
}
