package com.teamonline.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

/**
 * The thymeleaf frameworks needs this bean to be able to user security directly in the views
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
