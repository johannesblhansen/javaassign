package com.teamonline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.teamonline.model.AppUser;
import com.teamonline.service.AppUserService;

@Controller
public class RegisterController {
	
	@Autowired
	private AppUserService appUserService;
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String addAppUserFormHandling(Model model){
		model.addAttribute("appUserFromInput", new AppUser());
		return "register";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String addAppUserFormHandling(@ModelAttribute AppUser appUserFromInput, Model model){
		model.addAttribute("appUserFromInput", appUserFromInput);
		appUserService.registerUser(appUserFromInput);
		return "userCreated";
	}
}
