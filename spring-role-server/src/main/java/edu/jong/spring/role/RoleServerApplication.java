package edu.jong.spring.role;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import edu.jong.spring.common.constants.Packages;

@EnableDiscoveryClient
@EnableFeignClients(basePackages = Packages.ROOT)
@SpringBootApplication(scanBasePackages = Packages.ROOT)
public class RoleServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoleServerApplication.class, args);
	}
}
 