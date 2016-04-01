package com.teamonline.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.teamonline.model.AppUserFromInput;

@Controller
public class AppUserController {
	
	@RequestMapping(value="/createAppUser", method=RequestMethod.POST)
	public String addAppUserFormHandling(@ModelAttribute AppUserFromInput appUserFromInput, Model model){
		model.addAttribute("AppUserFromInput", appUserFromInput);
		return "userCreated";
	}
}
