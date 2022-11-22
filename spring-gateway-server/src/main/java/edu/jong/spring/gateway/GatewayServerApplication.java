package edu.jong.spring.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import edu.jong.spring.common.constants.Packages;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = Packages.ROOT)
public class GatewayServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServerApplication.class, args);
	}
}
