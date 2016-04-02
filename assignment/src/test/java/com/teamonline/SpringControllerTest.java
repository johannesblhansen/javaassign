package com.teamonline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebAppConfiguration
public abstract class SpringControllerTest extends BaseSpringTest {
	
	protected MockMvc mockMvc;
	
	@Autowired
	protected WebApplicationContext webAppConfiguration;
	
	protected void setUp(){
		mockMvc = MockMvcBuilders.webAppContextSetup(webAppConfiguration).build();
	}
}
