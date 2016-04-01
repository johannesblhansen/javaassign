package com.teamonline.security;

import java.util.ArrayList;
import java.util.Collection;

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


@Service
public class SimpleAppUserDetailsService implements UserDetailsService {
	
	@Autowired
	private AppUserService appUserService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		AppUser appUser = appUserService.findUserByName(username);
		if (appUser == null){
			return null;
		}
		
		//For the purpose of this super simple app, we will ignore granted authorization, and only
		//give a single simple authority for everything.
		Collection<GrantedAuthority> grantedAuthority = new ArrayList<GrantedAuthority>();
		grantedAuthority.add(new SimpleGrantedAuthority("ALL"));

		User userDetails = new User(appUser.getUsername(), appUser.getPassword(), grantedAuthority);

		return userDetails;
	}

}
