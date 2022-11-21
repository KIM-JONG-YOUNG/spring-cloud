package edu.jong.spring.login.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import edu.jong.spring.login.model.SessionDetails;

public interface LoginService extends UserDetailsService {

	@Override
	SessionDetails loadUserByUsername(String username) throws UsernameNotFoundException;

}
