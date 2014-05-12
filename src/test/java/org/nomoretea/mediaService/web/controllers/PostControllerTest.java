package org.nomoretea.mediaService.web.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nomoretea.mediaService.TestUtil;
import org.nomoretea.mediaService.spring.PersistenceConfig;
import org.nomoretea.mediaService.spring.WebConfig;
import org.nomoretea.mediaService.web.dto.PostDTO;
import org.nomoretea.mediaService.web.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class, PersistenceConfig.class})
@WebAppConfiguration
public class PostControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.build();
	}

	@Test
	public void testSuccessfullyGetPost() throws Exception {

		mockMvc.perform(get("/post/1"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8));
	}

	@Test
	public void testSuccessfullyCreatedPost() throws Exception {

		UserDTO userDTO = new UserDTO();
		userDTO.setId(1L);

		PostDTO postDTO = new PostDTO("Title", "Description", "Content", userDTO);
		byte[] post = TestUtil.convertObjectToJsonBytes(postDTO);

		mockMvc.perform(post("/post")
				.contentType(TestUtil.APPLICATION_JSON_UTF8)
				.content(post)
			)
				.andExpect(status().isCreated())
				.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8));
	}

	@Test
	public void testSuccessfullyUpdatedPost() throws Exception {

		UserDTO userDTO = new UserDTO();
		userDTO.setId(1L);

		PostDTO postDTO = new PostDTO("Title", "Description", "Content", userDTO);
		byte[] post = TestUtil.convertObjectToJsonBytes(postDTO);

		mockMvc.perform(put("/post/" + 1)
				.contentType(TestUtil.APPLICATION_JSON_UTF8)
				.content(post)
		)
				.andExpect(status().isOk())
				.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8));

	}

	@Test
	public void testSuccessfullyDeletePost() throws Exception {

		mockMvc.perform(delete("/post/" + 1))
				.andExpect(status().isNoContent());

	}

}
