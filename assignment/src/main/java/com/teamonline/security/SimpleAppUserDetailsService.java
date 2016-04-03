package com.teamonline.security;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.teamonline.model.AppUser;
import com.teamonline.service.AppUserService;

/**
 * Service used to handle user lookup and  rather than doing directly in the DAO (AppUserRepository) which should be
 * isolated from spring framework org.springframework.security.*;
 * @author Johannes
 *
 */
@Service
public class SimpleAppUserDetailsService implements UserDetailsService {
	
	private static Logger logger = Logger.getLogger(SimpleAppUserDetailsService.class);
	
	@Autowired
	private AppUserService appUserService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {		
		AppUser appUser = appUserService.findUserByName(username);
		
		if (appUser == null){
			logger.info("Logging attempt failled for user: " + username + "  couldn't find user with name that name");
			throw new UsernameNotFoundException("Couldn't find user with name: " + username);
		}
		
		//For the purpose of this super simple app, we will ignore granted authorization, and only
		//give a single simple authority for everything. 
		Collection<GrantedAuthority> grantedAuthority = new ArrayList<GrantedAuthority>();
		grantedAuthority.add(new SimpleGrantedAuthority("ALL"));

		User user = new User(appUser.getUsername(), appUser.getPassword(), grantedAuthority);
		return user;
	}

}
