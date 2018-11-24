package com.example.webservices.restservices.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webservices.restservices.dao.UserDaoService;
import com.example.webservices.restservices.exceptions.UserNotFoundException;
import com.example.webservices.restservices.model.User;

@RestController
public class UserController {

	@Autowired
	private UserDaoService service;

	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return this.service.findAll();
	}

	@GetMapping("/users/{id}")
	public User retrieveUser(@PathVariable("id") int id) {
		User foundUser = this.service.findOne(id);

		if (foundUser == null) {
			throw new UserNotFoundException("User " + id + " not found");
		}
		return foundUser;
	}

	@PostMapping("/users")
	public Resource<User> createUser(@Valid @RequestBody User user) {
		User newUser = this.service.save(user);

		Resource<User> resource = new Resource<User>(newUser);

		ControllerLinkBuilder link = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());

		resource.add(link.withRel("users"));

		return resource;

	}

	@DeleteMapping("/users/{id}")
	public String deleteUser(@PathVariable("id") int id) {
		User deletedUser = this.service.deleteById(id);

		if (deletedUser == null) {
			throw new UserNotFoundException("User " + id + " not found");
		}
		return "User deleted successfuly";
	}

}
