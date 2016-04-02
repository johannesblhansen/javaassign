package com.teamonline.controller;

import static org.junit.Assert.assertEquals;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.teamonline.SpringControllerTest;

@Transactional
public class RegisterControllerTest extends SpringControllerTest {
	
	@Before
	public void setup(){
		super.setUp();
	}
	
	@Test
	public void httpTest() throws Exception{
		String uri = "/home";		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.TEXT_HTML)).andReturn();
		int status = result.getResponse().getStatus();
		assertEquals("Status of getting the homepage should 200", 200, status);
	}
}
