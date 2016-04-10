package com.teamonline.service;

import com.teamonline.model.PasswordStrengthModel;

public interface PasswordStrengthService {
	
	PasswordStrengthModel isPasswordStrong(String password);

}
