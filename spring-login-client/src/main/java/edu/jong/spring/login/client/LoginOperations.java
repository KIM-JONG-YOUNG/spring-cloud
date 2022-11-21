package edu.jong.spring.login.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import edu.jong.spring.common.constants.HeaderNames;
import edu.jong.spring.common.constants.ServiceNames;
import edu.jong.spring.login.request.LogingParam;
import edu.jong.spring.login.response.SessionTokens;

@FeignClient(ServiceNames.LOGIN_SERVICE)
public interface LoginOperations {

	@PostMapping(value = "/login",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<SessionTokens> login(
			@RequestBody LogingParam param);
	
	@PostMapping(value = "/logout")
	ResponseEntity<Void> logout(
			@RequestHeader(HeaderNames.AUTH_TOKEN) String accessToken);
	
	@GetMapping(value = "/check")
	ResponseEntity<Void> check(
			@RequestHeader(HeaderNames.AUTH_TOKEN) String accessToken,
			@RequestHeader(HeaderNames.ACCESSIBLE_URL) String accessUrl);

	@PostMapping(value = "/refresh")
	ResponseEntity<SessionTokens> refresh(
			@RequestHeader(HeaderNames.AUTH_TOKEN) String accessToken,
			@RequestHeader(HeaderNames.REFRESH_TOKEN) String refreshToken);

}
