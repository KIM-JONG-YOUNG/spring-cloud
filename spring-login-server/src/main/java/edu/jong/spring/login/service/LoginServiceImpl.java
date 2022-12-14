package edu.jong.spring.login.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import edu.jong.spring.common.constants.CacheNames;
import edu.jong.spring.login.model.SessionDetails;
import edu.jong.spring.login.request.AccessCheckParam;
import edu.jong.spring.login.request.LogingParam;
import edu.jong.spring.login.response.SessionTokens;
import edu.jong.spring.member.client.MemberOperations;
import edu.jong.spring.member.response.MemberDetails;
import edu.jong.spring.redis.service.RedisService;
import edu.jong.spring.role.client.RoleOperations;
import edu.jong.spring.role.enums.APIMethod;
import edu.jong.spring.role.response.RoleDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

	private final MemberOperations memberOperations;
	private final RoleOperations roleOperations ;
	private final RedisService redisService;
	private final PasswordEncoder encoder;
	private final AntPathMatcher antPathMatcher = new AntPathMatcher();
	
	@Override
	public SessionDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		ResponseEntity<MemberDetails> response = memberOperations.get(username);
		
		if (response.getStatusCode() != HttpStatus.OK) 
			throw new UsernameNotFoundException("존재하지 않는 계정입니다.");

		MemberDetails details = response.getBody();
		return SessionDetails.builder()
				.no(details.getNo())
				.username(details.getUsername())
				.password(details.getPassword())
				.build();
	}

	@Override
	public String generateAccessToken(long memberNo) {
		
		Date now = new Date();
        return Jwts.builder()
        		.setClaims(Jwts.claims()
            		.setSubject(String.valueOf(memberNo)))
        		.setIssuedAt(now)
        		.setExpiration(new Date(now.getTime() + 60 * 1000))
        		.signWith(SignatureAlgorithm.HS512, "SECRET_KEY") 
        		.compact();	
	}

	@Override
	public String generateRefreshToken(String accessToken) {
		
		Date now = new Date();
		String refreshToken = Jwts.builder()
        		.setIssuedAt(now)
        		.setExpiration(new Date(now.getTime() + 600 * 1000))
        		.signWith(SignatureAlgorithm.HS512, "SECRET_KEY") 
        		.compact();
        
		redisService.caching(CacheNames.REFRESH_TOKEN + refreshToken, accessToken, 600);
		
		return refreshToken;	

	}

	@Override
	public boolean validateAccessToken(String accessToken) {

		try {
			Claims claims = Jwts.parser()
					.setSigningKey("SECRET_KEY")
					.parseClaimsJws(accessToken)
					.getBody();

			if (StringUtils.isNumeric(claims.getSubject())) 
				throw new MalformedJwtException("형식에 맞지 않는 토큰입니다.");
		
			if (redisService.has(CacheNames.BLACKLIST + accessToken)) 
				throw new RuntimeException("로그아웃 된 토큰입니다.");
				
		} catch (Exception e) {
			
			log.warn("Exception Class => {} | Message => {}", e.getClass(), e.getMessage());
			
			return false;
		}
		
		return true;
	}

	@Override
	public boolean validateRefreshToken(String accessToken, String refreshToken) {

		try {
			Claims claims = Jwts.parser()
					.setSigningKey("SECRET_KEY")
					.parseClaimsJws(accessToken)
					.getBody();

			String redisAccessToken = redisService.get(CacheNames.REFRESH_TOKEN + refreshToken)
					.orElseThrow(() -> new ExpiredJwtException(null, claims, "유효기간이 지난 Refresh Token 입니다."));
			
			if (!redisAccessToken.equals(accessToken))
				throw new RuntimeException("AccessToken과 Refresh 토큰이 일치하지 않습니다.");
		
		} catch (Exception e) {
			
			log.warn("Exception Class => {} | Message => {}", e.getClass(), e.getMessage());
			
			return false;
		}

		return true;
	}

	@Override
	public SessionTokens login(LogingParam param) {

		SessionDetails details = loadUserByUsername(param.getUsername());
		
		if (encoder.matches(param.getPassword(), details.getPassword())) {
			
			String accessToken = generateAccessToken(details.getNo());
			String refreshToken = generateRefreshToken(accessToken);
			
			return SessionTokens.builder()
					.accessToken(accessToken)
					.refreshToken(refreshToken)
					.build();
		} else {
			throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
		}
	}

	@Override
	public void logout(String accessToken) {
		redisService.caching(CacheNames.BLACKLIST + accessToken, "logout", 600);
	}

	@Override
	public long getMemberNoFromAccessToken(String accessToken) {
		
		Claims claims = null;
		
		try {
			claims = Jwts.parser()
					.setSigningKey("SECRET_KEY")
					.parseClaimsJws(accessToken)
					.getBody();
		} catch (ExpiredJwtException e) {
			claims = e.getClaims();
		}
		
		return Long.parseLong(claims.getSubject());
	}

	@Override
	public SessionTokens refreshToken(String accessToken, String refreshToken) {
		
		if (validateRefreshToken(accessToken, refreshToken)) {
			
			long memberNo = getMemberNoFromAccessToken(accessToken);
			String newAccessToken = generateAccessToken(memberNo);
			String newRefreshToken = generateRefreshToken(newAccessToken);
			
			redisService.remove(CacheNames.REFRESH_TOKEN + refreshToken);
			
			return SessionTokens.builder()
					.accessToken(newAccessToken)
					.refreshToken(newRefreshToken)
					.build();
		} else {
			throw new RuntimeException("유효하지 않은 Refresh Token 입니다.");
		}
	}

	@Override
	public boolean checkAccessible(String accessToken, AccessCheckParam param) {

		long memberNo = getMemberNoFromAccessToken(accessToken);
		ResponseEntity<List<RoleDetails>> response = roleOperations.getAllByMember(memberNo);

		if (response.getStatusCode() != HttpStatus.OK) 
			throw new RuntimeException("권한을 조회하는데 실패했습니다.");

		boolean isAccessibleMethod = false;
		boolean isAccessibleURL = false;
		
		for (RoleDetails roleDetails : response.getBody()) {
			
			isAccessibleMethod = (roleDetails.getAccessibleMethod() == APIMethod.ALL) 
					|| (roleDetails.getAccessibleMethod() == param.getCheckMethod());
			isAccessibleURL = antPathMatcher.match(roleDetails.getAccessibleUrlPattern(), param.getCheckURL());

			if (isAccessibleMethod && isAccessibleURL) break;
		}
		
		return (isAccessibleMethod && isAccessibleURL);
	}

}
