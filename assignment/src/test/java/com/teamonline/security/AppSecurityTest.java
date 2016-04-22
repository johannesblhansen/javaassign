package com.teamonline.security;

import static org.junit.Assert.assertEquals;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.*;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.teamonline.SpringControllerTest;

public class AppSecurityTest extends SpringControllerTest {	
	
	@Autowired
	private WebApplicationContext webApplicationContext; 

	private MockMvc mockMvc;
	
	@Before
	public void setup(){
		mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
	}
	
	@Test
	@WithUserDetails("user")
	public void gettingArestrictedResourceWithAuser() throws Exception{
		String uri = "/resource";		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.TEXT_HTML)).andReturn();
		int status = result.getResponse().getStatus(); //Expecting a redirect to login page.
		assertEquals("Getting the secure resource " + uri + " with a user should return 200", 200, status);
	}
	
	@Test
	// not using the user annotation and trying to get the resource
	public void gettingArestrictedResourceWithoutAuser() throws Exception{
		String uri = "/resource";		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.TEXT_HTML)).andReturn();
		int status = result.getResponse().getStatus(); //Expecting a redirect to login page.
		assertEquals("Status of getting the resource page without auth should give 302", 302, status);
	}
	
	/**
	 * Using springs request builder to test login. From the spring doc:
	 * Spring MVC Test also provides a RequestBuilder interface that can be used to create the 
	 * MockHttpServletRequest used in your test. Spring Security provides a few RequestBuilder 
	 * implementations that can be used to make testing easier.
	 * @throws Exception
	 */
	@Test
	public void testingLoginFunc() throws Exception{
		//Using the standard /login to do a mock form login
		//Using a ResultMatcher to check the result
		mockMvc.perform(formLogin().user("user").password("pass")).andExpect(authenticated());
		mockMvc.perform(formLogin().user("user").password("notvalid")).andExpect(unauthenticated());
	}
}
