package com.teamonline.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;

@Component
public class AccountAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	@Autowired
	private AccountUserDetailsService userDetailsService;

//	@Autowired
//	private PasswordEncoder passwordEncoder;

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken token)
			throws AuthenticationException {
		
		//Password cannot be null in either user from server, or user input user info.
		if(token.getCredentials() == null || userDetails.getPassword() == null){
			throw new BadCredentialsException("Authentication Error");
		}
		
		
		if (!userDetails.getPassword().equals(token.getCredentials())){
			throw new BadCredentialsException("Incorrect Password");
		}
		

//		
//		if (!passwordEncoder.matches((String) token.getCredentials(), userDetails.getPassword())){
//			throw new BadCredentialsException("Bad Password");
//		}
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken token)
			throws AuthenticationException {
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		return userDetails;
	}

}
