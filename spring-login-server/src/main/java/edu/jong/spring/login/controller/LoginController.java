package edu.jong.spring.login.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import edu.jong.spring.login.client.LoginOperations;
import edu.jong.spring.login.request.AccessCheckParam;
import edu.jong.spring.login.request.LogingParam;
import edu.jong.spring.login.response.SessionTokens;
import edu.jong.spring.login.service.LoginService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LoginController implements LoginOperations {

	private final LoginService service;
	
	@Override
	public ResponseEntity<SessionTokens> login(LogingParam param) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(service.login(param));
	}

	@Override
	public ResponseEntity<Void> logout(String accessToken) {

		service.logout(accessToken);
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT)
				.build();
	}

	@Override
	public ResponseEntity<Boolean> check(String accessToken, AccessCheckParam param) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(service.checkAccessible(accessToken, param));
	}

	@Override
	public ResponseEntity<SessionTokens> refresh(String accessToken, String refreshToken) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(service.refreshToken(accessToken, refreshToken));
	}

}
