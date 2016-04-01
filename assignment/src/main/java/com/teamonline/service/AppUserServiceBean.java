package com.teamonline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.teamonline.model.AppUser;
import com.teamonline.repository.UserRepository;

@Service
public class AppUserServiceBean implements AppUserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public AppUser findUserByName(String username) {
		AppUser user = userRepository.findByUsername(username);
		return user;
	}
	
	@Override
	public void registerUser(AppUser appUser) {
		// TODO add some sort of error handling here
		// user cant already exist
		// validate the inputs
		
		String clearTextPassword = appUser.getPassword();
		appUser.setPassword("");
		String encodedPassword = passwordEncoder.encode(clearTextPassword);
		appUser.setPassword(encodedPassword);
		userRepository.save(appUser);
	}
}
