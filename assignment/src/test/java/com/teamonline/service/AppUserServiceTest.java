package com.teamonline.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.teamonline.BaseSpringTest;
import com.teamonline.model.AppUser;

import static org.junit.Assert.*;

/**
 * Testing services from the application.
 * @author Johannes
 *
 */
@Transactional //The transactional annotation makes the tests happen in a transaction. IE. Rollback state after every test.
public class AppUserServiceTest extends BaseSpringTest {

	//It is possible to do autowireing since we extend the abstract test class.
	@Autowired
	private AppUserService appUserService;

	@Before
	public void setup() {

	}

	@Test
	public void findAnAppUserFromUserName() {
		AppUser appUser = appUserService.findUserByName("user");
		assertNotNull("The AppUserService should be able to find a user with name user after initial setup", appUser);
	}
}
