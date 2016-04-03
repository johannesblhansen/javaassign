package com.teamonline.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.teamonline.service.PasswordStrengthService;

/**
 * Rest service for checking strength of a password.
 * @author Johannes
 *
 */
@RestController
public class PasswordStrenghtController {
	
	@Autowired
	private PasswordStrengthService passwordStrengthService;
	
	@RequestMapping(value="/rest/strength/{password}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Boolean addAppUserFormHandling(@PathVariable("password") String password){
		return passwordStrengthService.isPasswordStrong(password);
	}
}
