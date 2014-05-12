package org.nomoretea.mediaService.web.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nomoretea.mediaService.TestUtil;
import org.nomoretea.mediaService.spring.PersistenceConfig;
import org.nomoretea.mediaService.spring.WebConfig;
import org.nomoretea.mediaService.web.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class, PersistenceConfig.class})
@WebAppConfiguration
public class UserControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.build();
	}

	@Test
	public void testSuccessfullyCreatedUser() throws Exception
	{
		UserDTO userDTO = new UserDTO();
		userDTO.setEmail("test@ggg.com");

		byte[] user = TestUtil.convertObjectToJsonBytes(userDTO);

		mockMvc.perform(post("/user")
						.contentType(TestUtil.APPLICATION_JSON_UTF8)
						.content(user)
		)
				.andExpect(status().isCreated())
				.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8));
	}
}