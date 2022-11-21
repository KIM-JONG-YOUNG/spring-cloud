package edu.jong.spring.login.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import edu.jong.spring.login.model.LogingParam;
import edu.jong.spring.login.model.SessionTokens;

@FeignClient("login-service")
public interface LoginOperations {

	public static final String AUTH_TOKEN_HEADER_NAME = "Authorization-Token";
	public static final String REFRESH_TOKEN_HEADER_NAME = "Refresh-Token";
	
	@PostMapping(value = "/login",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<SessionTokens> login(
			@RequestBody LogingParam param);
	
	@PostMapping(value = "/logout")
	ResponseEntity<Void> logout(
			@RequestHeader(AUTH_TOKEN_HEADER_NAME) String accessToken);
	
	@GetMapping(value = "/check")
	ResponseEntity<Void> check(
			@RequestHeader(AUTH_TOKEN_HEADER_NAME) String accessToken,
			@RequestHeader(HttpHeaders.ORIGIN) String accessUrl);

	@PostMapping(value = "/refresh")
	ResponseEntity<SessionTokens> refresh(
			@RequestHeader(AUTH_TOKEN_HEADER_NAME) String accessToken,
			@RequestHeader(REFRESH_TOKEN_HEADER_NAME) String refreshToken);

}
