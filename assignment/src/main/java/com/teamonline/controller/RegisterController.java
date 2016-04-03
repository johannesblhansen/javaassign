package com.teamonline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.teamonline.ApplicationConstants;
import com.teamonline.model.AppUser;
import com.teamonline.service.AppUserRegistrationRuntimeException;
import com.teamonline.service.AppUserService;

@Controller
public class RegisterController {

	@Autowired
	private AppUserService appUserService;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String addAppUserFormHandling(Model model) {
		// We need to add a new app user to the model, so it can collect values
		// in the Thymeleaf view.
		model.addAttribute(ApplicationConstants.MODEL_APPUSER, new AppUser());
		return ApplicationConstants.REGISTER;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String addAppUserFormHandling(@ModelAttribute AppUser appUserFromInput, Model model) {
		model.addAttribute(ApplicationConstants.MODEL_APPUSER, appUserFromInput);
		try {
			appUserService.registerUser(appUserFromInput);
		} catch (AppUserRegistrationRuntimeException e) {
			model.addAttribute(ApplicationConstants.MODEL_ERROR, e.getMessage());
			return ApplicationConstants.REGISTER;
		}
		return ApplicationConstants.USERCREATED;
	}
}
