package com.teamonline.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.teamonline.BaseSpringTest;
import com.teamonline.model.AppUser;

import static org.junit.Assert.*;

/**
 * Testing services from the application.
 * 
 * This test uses the already created user set up by data initialization sql on the hsqldb.
 * Since this test uses the BaseSpringTest, the initialization is run before test are executed
 * @author Johannes
 *
 */
@Transactional //The transactional annotation makes the tests happen in a transaction. IE. Rollback state after every test.
public class AppUserServiceTest extends BaseSpringTest {

	//It is possible to do autowireing since we extend the abstract test class.
	@Autowired
	private AppUserService appUserService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Test
	public void findAnAppUserFromUserName() {
		AppUser appUser = appUserService.findUserByName("user");
		assertNotNull("The AppUserService should be able to find a user with name 'user' after initial setup", appUser);
		
		appUser = appUserService.findUserByName("othername");
		assertNull("The AppUserService should not be able to find a user with name 'othername' after initial setup", appUser);
		
		appUser = appUserService.findUserByName("");
		assertNull("The AppUserService should not be able to find a user with an empty string as name", appUser);
		
		appUser = appUserService.findUserByName(null);
		assertNull("The AppUserService should not be able to find a user with null as input", appUser);
	}
	
	@Test
	public void createUserTestCorrectSetup() {
		String name = "user1";
		String password = "1qaz1qaZ";
		AppUser setupUser = new AppUser();
		setupUser.setUsername(name);
		setupUser.setPassword(password);
		appUserService.registerUser(setupUser);
		
		AppUser foundUser = appUserService.findUserByName(name);
		assertNotNull("Should be able to find the newly created user", foundUser);
	}
	
	//Since the password service itself is tested elsewhere, it is simply enough to test that
	//the expected exception is thrown on rejection
	@Test(expected=AppUserRegistrationRuntimeException.class)
	public void createUserTestWrongPassword() {
		//password is too short
		String name = "user1";
		String password = "1qaz1qA"; //password is too short
		AppUser setupUser = new AppUser();
		setupUser.setUsername(name);
		setupUser.setPassword(password);
		appUserService.registerUser(setupUser);
	}
	
	@Test(expected=AppUserRegistrationRuntimeException.class)
	public void createUserAlreadyExists() {
		String name = "user"; //This user is setup in data initialization
		String password = "1qaz1qaZ";
		AppUser setupUser = new AppUser();
		setupUser.setUsername(name);
		setupUser.setPassword(password);
		appUserService.registerUser(setupUser);
	}
	
	@Test(expected=AppUserRegistrationRuntimeException.class)
	public void createUserEmptyUsername() {
		String name = "";
		String password = "1qaz1qaZ";
		AppUser setupUser = new AppUser();
		setupUser.setUsername(name);
		setupUser.setPassword(password);
		appUserService.registerUser(setupUser);
	}
	
	@Test(expected=AppUserRegistrationRuntimeException.class)
	public void createUserNullUsername() {
		String name = null;
		String password = "1qaz1qaZ";
		AppUser setupUser = new AppUser();
		setupUser.setUsername(name);
		setupUser.setPassword(password);
		appUserService.registerUser(setupUser);
	}
	
	//Checking that user password is encoded correctly
	@Test
	public void createUserTestEncoding() {
		String name = "user1";
		String password = "1qaz1qaZ";
		AppUser setupUser = new AppUser();
		setupUser.setUsername(name);
		setupUser.setPassword(password);
		appUserService.registerUser(setupUser);
		
		AppUser foundUser = appUserService.findUserByName(name);
		//Encoder should now be able to match passwords
		assertTrue(passwordEncoder.matches(password,  foundUser.getPassword()));
	}	
}
