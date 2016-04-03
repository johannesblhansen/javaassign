package com.teamonline.security;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Spring security point of IoC. Application specific user lookup and authentication is added here.
 * @author Johannes
 *
 */
@Component
public class SimpleLoginAppAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
	
	private static Logger logger = Logger.getLogger(SimpleLoginAppAuthenticationProvider.class);

	@Autowired
	private SimpleAppUserDetailsService userDetailsService;

	/**
	 * Hashes passwords, and can also match clear text passwords with hashed passwords
	 * For details look at security config class for the instantiation of the injected password encoder class.
	 */
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken token)
			throws AuthenticationException {		
		//Password cannot be null in either user from server, or user input user info.
		if(token.getCredentials() == null || userDetails.getPassword() == null){
			logger.info("Logging attempt failed for user: " + userDetails.getUsername() + " password from input or db is null");
			throw new BadCredentialsException("Authentication Error");
		}
		
		if (!passwordEncoder.matches((String) token.getCredentials(), userDetails.getPassword())){
			logger.info("Logging attempt failed for user: " + userDetails.getUsername() + " bad password");
			throw new BadCredentialsException("Bad Password");
		}
		logger.info("Logging attempt success for user: " + userDetails.getUsername());
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken token)
			throws AuthenticationException {
		logger.info("Logging attempt started for user: " + username);
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		return userDetails;
	}
}
