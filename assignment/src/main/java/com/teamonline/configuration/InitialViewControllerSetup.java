package com.teamonline.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Adds view controllers to the application
 * @author Johannes
 *
 */
@Configuration
public class InitialViewControllerSetup extends WebMvcConfigurerAdapter {
	
	/**
	 * Skipping the MVC patterns' controller using a viewcontroller instead.
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/home").setViewName("home");
		registry.addViewController("/").setViewName("home");
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/register").setViewName("register");
		registry.addViewController("/userCreated").setViewName("userCreated");
		//registry.addViewController("/resource").setViewName("resource");
	}
}