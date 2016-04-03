package com.teamonline.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.teamonline.util.ApplicationConstants;

/**
 * Adds view controllers to the application at specific paths
 * @author Johannes
 *
 */
@Configuration
public class InitialViewControllerSetup extends WebMvcConfigurerAdapter {
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/home").setViewName(ApplicationConstants.HOME);
		registry.addViewController("/").setViewName(ApplicationConstants.HOME);
		registry.addViewController("/login").setViewName(ApplicationConstants.LOGIN);
		registry.addViewController("/register").setViewName(ApplicationConstants.REGISTER);
		registry.addViewController("/resource").setViewName(ApplicationConstants.RESOURCE);
	}	
}