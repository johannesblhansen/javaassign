package com.teamonline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamonline.model.AppUser;
import com.teamonline.repository.UserRepository;

@Service
public class AppUserServiceBean implements AppUserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public AppUser findUserByName(String username) {
		AppUser user = userRepository.findByUsername(username);
		return user;
	}
}
