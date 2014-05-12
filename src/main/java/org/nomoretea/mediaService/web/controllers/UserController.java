package org.nomoretea.mediaService.web.controllers;

import org.nomoretea.mediaService.persistence.model.User;
import org.nomoretea.mediaService.persistence.service.IUserService;
import org.nomoretea.mediaService.web.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private IUserService userService;

	@RequestMapping(method = RequestMethod.GET, value = "")
	public ResponseEntity<List> getAllUsers()
	{
		HttpHeaders responseHeaders = new HttpHeaders();

		List<User> users = userService.findAll();
		List<UserDTO> userDTOs = new ArrayList<UserDTO>(users.size());

		for (User user: users)
		{
			userDTOs.add(new UserDTO(user));
		}

		return new ResponseEntity<List>(userDTOs, responseHeaders, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{userId}")
	public ResponseEntity<UserDTO> getUser(@PathVariable("userId") Long userId)
	{
		HttpHeaders responseHeaders = new HttpHeaders();
		User user = userService.findOne(userId);

		if(user == null)
			throw new EntityNotFoundException();

		return new ResponseEntity<UserDTO>(new UserDTO(user), responseHeaders, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{userId}")
	public void deleteUser(@PathVariable("userId") Long userId)
	{
		User user = userService.findOne(userId);

		if(user == null)
			throw new EntityNotFoundException();

		userService.deleteById(userId);
	}

	@RequestMapping(method = RequestMethod.POST, value = "")
	public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO)
	{
		HttpHeaders responseHeaders = new HttpHeaders();

		userDTO.setId(null);
		UserDTO responseDTO = new UserDTO(userService.create(new User(userDTO)));

		return new ResponseEntity<UserDTO>(responseDTO, responseHeaders, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{userId}")
	public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO, @PathVariable("userId") Long userId)
	{
		HttpHeaders responseHeaders = new HttpHeaders();

		User user = userService.findOne(userId);

		if(user == null)
			throw new EntityNotFoundException();

		userDTO.setId(userId);
		UserDTO responseDTO = new UserDTO(userService.create(new User(userDTO)));

		return new ResponseEntity<UserDTO>(responseDTO, responseHeaders, HttpStatus.CREATED);
	}

}
