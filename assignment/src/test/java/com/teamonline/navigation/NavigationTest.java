package com.teamonline.navigation;

import static org.junit.Assert.assertEquals;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.teamonline.SpringControllerTest;

public class NavigationTest extends SpringControllerTest {
	
	@Before
	public void setup(){
		super.setUp();
	}
	
	@Test
	public void getTheHomepageTemplateTest() throws Exception{
		String uri = "/home";		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.TEXT_HTML)).andReturn();
		int status = result.getResponse().getStatus();
		assertEquals("Status of getting the homepage by /home should 200", 200, status);
		
		uri = "/";		
		result = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.TEXT_HTML)).andReturn();
		status = result.getResponse().getStatus();
		assertEquals("Status of getting the homepage by / should 200", 200, status);
	}
	
	@Test
	public void getTheLoginTemplateTest() throws Exception{
		String uri = "/login";		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.TEXT_HTML)).andReturn();
		int status = result.getResponse().getStatus();
		assertEquals("Status of getting the loginpage should 200", 200, status);
	}
	
	@Test
	public void getTheRegisterTemplateTest() throws Exception{
		String uri = "/register";		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.TEXT_HTML)).andReturn();
		int status = result.getResponse().getStatus();
		assertEquals("Status of getting the registerpage should 200", 200, status);
	}
	
	//This resource should not be available without auth
	@Test
	public void getTheResourceTemplateTest() throws Exception{
		String uri = "/resource";		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.TEXT_HTML)).andReturn();
		int status = result.getResponse().getStatus(); //Expecting a redirect to login page.
		assertEquals("Status of getting the resource page without auth should give 302", 302, status);
	}
	
	//This resource should not be available without auth
	@Test
	public void getTheResourceTemplateWithAuth() throws Exception{
		String uri = "/resource";		
		//MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.TEXT_HTML)).andReturn();
		mockMvc.perform(get("/").with(user("user")));
		//int status = result.getResponse().getStatus(); //Expecting a redirect to login page.
		//assertEquals("Status of getting the resource page without auth should give 302", 302, status);
	}
}
