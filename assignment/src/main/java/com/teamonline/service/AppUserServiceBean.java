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
	
	@Autowired
	private PasswordStrengthService passwordStrengthService;
	
	/**
	 * Looks up a username in the database.
	 */
	@Override
	public AppUser findUserByName(String username) {
		AppUser user = userRepository.findByUsername(username);
		return user;
	}
	
	/**
	 * Registers a new user.
	 * If the user already exist, or the password is not strong enough an
	 * AppUserRegistrationRuntimeException execption is thrown.
	 */
	@Override
	public void registerUser(AppUser appUser) {
		String username = appUser.getUsername();
		if (username == null){
			throw new AppUserRegistrationRuntimeException("Username cannot be null");
		}
		if (userRepository.findByUsername(username) != null){
			throw new AppUserRegistrationRuntimeException("Username already exist");
		}
		
		String clearTextPassword = appUser.getPassword();
		if (!passwordStrengthService.isPasswordStrong(clearTextPassword)){
			throw new AppUserRegistrationRuntimeException("Password is not strong enough");
		}
		
		//Replacing the cleartext password with an encoded password.
		appUser.setPassword("");
		String encodedPassword = passwordEncoder.encode(clearTextPassword);
		appUser.setPassword(encodedPassword);
		userRepository.save(appUser);
	}
}
