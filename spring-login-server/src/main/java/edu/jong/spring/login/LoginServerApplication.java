package edu.jong.spring.login;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import edu.jong.spring.common.constants.Packages;

@EnableDiscoveryClient
@EnableFeignClients(basePackages = Packages.ROOT)
@SpringBootApplication(scanBasePackages = Packages.ROOT)
public class LoginServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginServerApplication.class, args);
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity.httpBasic().disable()
				.formLogin().disable()
				.cors().configurationSource(corsConfigurationSource())
				.and().csrf().disable()
				.headers().frameOptions().disable()
				.and().sessionManagement()		
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS) 
				.and().headers().frameOptions().disable()	// h2 디비 설정
//				.and().addFilterBefore(tokeCheckFilter, UsernamePasswordAuthenticationFilter.class)
				.and()
				.authorizeRequests().anyRequest().permitAll()
				.and().build();
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
//		configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.addAllowedOrigin("*");
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
		configuration.addAllowedHeader("*");
		configuration.addExposedHeader("*");
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}
