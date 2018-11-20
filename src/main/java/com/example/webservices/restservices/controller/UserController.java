package com.example.webservices.restservices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webservices.restservices.dao.UserDaoService;
import com.example.webservices.restservices.model.User;

@RestController
public class UserController {

	@Autowired
	private UserDaoService service;

	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return this.service.findAll();
	}

	@GetMapping("/user/{id}")
	public User retrieveUser(@PathVariable("id") int id) {
		return this.service.findOne(id);
	}

	@PostMapping("/users")
	public void createUser(@RequestBody User user) {
		this.service.save(user);
	}
}
