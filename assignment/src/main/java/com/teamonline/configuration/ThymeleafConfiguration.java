package com.teamonline.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

/**
 * The thymeleaf frameworks needs this bean to be able to use security directly in the views
 * Look for the name space in the template html
 * @author Johannes
 *
 */
@Configuration
public class ThymeleafConfiguration {
	
	@Bean
	public SpringSecurityDialect springSecurityDialect() {
		return new SpringSecurityDialect();
	}
}
