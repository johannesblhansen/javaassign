package com.teamonline.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.teamonline.security.SimpleLoginAppAuthenticationProvider;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * Configuring Spring Security in Spring Boot.
 * @author Johannes
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
    @Autowired
    private SimpleLoginAppAuthenticationProvider simpleLoginAppAuthenticationProvider;
    
    /**
     * This will instruct spring to inject the bcrypt password encoder, that we use in custom auth provider.
     * It uses a spring recomended implementation of bcrypt. Details overview: https://en.wikipedia.org/wiki/Bcrypt
     */
    @Bean
    public PasswordEncoder getPasswordEncoder(){
    	return new BCryptPasswordEncoder();
    }
	
    /**
     * Configures security on resources
     */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(
											"/", 
											"/home", 
											"/register", 
											"/createAppUser", 
											"/userCreated", 
											"/css/*", 
											"/js/*", 
											"/rest/**")
												.permitAll().anyRequest().authenticated().and()
												.formLogin().loginPage("/login").permitAll().and()
												.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll();

	}

	/**
	 * Adds the application specific authentication provider.
	 * @param auth
	 * @throws Exception
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(simpleLoginAppAuthenticationProvider);
	}
}