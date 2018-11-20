package com.example.webservices.restservices.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		User newUser = this.service.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newUser.getId())
				.toUri();

		return ResponseEntity.created(location).build();

	}
}
