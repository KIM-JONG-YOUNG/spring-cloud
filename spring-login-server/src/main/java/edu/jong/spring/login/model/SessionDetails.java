package edu.jong.spring.login.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class SessionDetails implements UserDetails, CredentialsContainer {

	private static final long serialVersionUID = 1L;

	private String username;
	private String password;

	private final Set<GrantedAuthority> authorities = new HashSet<>();
	private final boolean accountNonExpired = true;
	private final boolean accountNonLocked = true;
	private final boolean credentialsNonExpired = true;
	private final boolean enabled = true;

	@Override
	public void eraseCredentials() {
		this.password = null;
	}

}
