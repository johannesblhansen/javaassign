package com.teamonline.controller;

import org.apache.log4j.Logger;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.hsqldb.HsqlException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.teamonline.model.AppUser;
import com.teamonline.service.AppUserRegistrationRuntimeException;
import com.teamonline.service.AppUserService;

@Controller
public class RegisterController {
	
	private static Logger logger = Logger.getLogger(RegisterController.class);

	@Autowired
	private AppUserService appUserService;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String addAppUserFormHandling(Model model) {
		// We need to add a new appuser to the model, so it can collect values
		// in the thymeleaf view.
		model.addAttribute("appUserFromInput", new AppUser());
		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String addAppUserFormHandling(@ModelAttribute AppUser appUserFromInput, Model model) {
		model.addAttribute("appUserFromInput", appUserFromInput);
		try {
			appUserService.registerUser(appUserFromInput);
		} catch (AppUserRegistrationRuntimeException e) {
			// TODO remove this hardcoded string
			model.addAttribute("creationError", e.getMessage());
			return "register";
		}
		return "userCreated";
	}
}
