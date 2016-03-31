package com.teamonline.service;

import com.teamonline.model.AppUser;

public interface AppUserService {
	
	AppUser findUserByName(String username);

}
