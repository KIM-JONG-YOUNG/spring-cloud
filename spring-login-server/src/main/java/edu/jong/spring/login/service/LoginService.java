package edu.jong.spring.login.service;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;

import edu.jong.spring.login.model.SessionDetails;
import edu.jong.spring.login.request.LogingParam;
import edu.jong.spring.login.response.SessionTokens;

@Validated
public interface LoginService extends UserDetailsService {

	@Override
	SessionDetails loadUserByUsername(@NotBlank String username) throws UsernameNotFoundException;

	String generateAccessToken(long memberNo);
	
	String generateRefreshToken(@NotBlank String accessToken);
	
	boolean validateAccessToken(@NotBlank String accessToken);

	boolean validateRefreshToken(@NotBlank String accessToken, @NotBlank String refreshToken);

	SessionTokens login(@NotNull @Valid LogingParam param);

	void logout(@NotBlank String accessToken);

}
