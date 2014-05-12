package org.nomoretea.mediaService.web.controllers;

import org.nomoretea.mediaService.persistence.model.Post;
import org.nomoretea.mediaService.persistence.model.User;
import org.nomoretea.mediaService.persistence.service.IPostService;
import org.nomoretea.mediaService.persistence.service.IUserService;
import org.nomoretea.mediaService.web.dto.PostDTO;
import org.nomoretea.mediaService.web.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/post")
public class PostController {

	@Autowired
	private IPostService postService;

	@Autowired
	private IUserService userService;

	@RequestMapping(method = RequestMethod.GET, value = "")
	public ResponseEntity<List> getPosts()
	{
		HttpHeaders responseHeaders = new HttpHeaders();

		List<Post> posts = postService.findAll();
		List<PostDTO> postDTOs = new ArrayList<PostDTO>();

		for(Post post: posts)
		{
			PostDTO postDTO = new PostDTO(post);
			postDTOs.add(postDTO);
		}

		return new ResponseEntity<List>(postDTOs, responseHeaders, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/user/{userId}")
	public ResponseEntity<List> getUserPosts(@PathVariable("userId") Long userId)
	{
		HttpHeaders responseHeaders = new HttpHeaders();

		User user = userService.findOne(userId);

		List<Post> posts = user.getPosts();
		List<PostDTO> postDTOs = new ArrayList<PostDTO>();

		for(Post post: posts)
		{
			PostDTO postDTO = new PostDTO(post.getId(), post.getTitle(), post.getContent(), post.getDescription());
			postDTOs.add(postDTO);
		}

		return new ResponseEntity<List>(postDTOs, responseHeaders, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{postId}")
	public ResponseEntity<PostDTO> getPost(@PathVariable("postId") final Long postId)
	{
		HttpHeaders responseHeaders = new HttpHeaders();
		Post post = postService.findOne(postId);

		if(post == null)
			throw new EntityNotFoundException();

		PostDTO postDTO = new PostDTO(post);
		postDTO.setUser(new UserDTO(post.getUser()));

		return new ResponseEntity<PostDTO>(postDTO, responseHeaders, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "")
	public ResponseEntity<PostDTO> createPost(@Valid @RequestBody final PostDTO postDTO)
	{
		HttpHeaders responseHeaders = new HttpHeaders();

		Post post = new Post(postDTO);
		post.setId(null);
		post.setUser(new User(postDTO.getUser()));

		return new ResponseEntity<PostDTO>(new PostDTO(postService.create(post)), responseHeaders, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{postId}")
	public ResponseEntity<PostDTO> updatePost(@Valid @RequestBody final PostDTO postDTO, @PathVariable("postId") Long postId)
	{
		HttpHeaders responseHeaders = new HttpHeaders();

		Post post = new Post(postDTO);
		post.setUser(new User(postDTO.getUser()));
		post.setId(postId);

		return new ResponseEntity<PostDTO>(new PostDTO(postService.update(post)), responseHeaders, HttpStatus.OK);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(method = RequestMethod.DELETE, value = "/{postId}")
	public void deletePost(@PathVariable("postId") Long postId)
	{
		postService.deleteById(postId);
	}

}
