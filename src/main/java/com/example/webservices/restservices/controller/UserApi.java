package com.example.webservices.restservices.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.example.webservices.restservices.model.User;

public interface UserApi {

	/**
	 * Get all the available users
	 * 
	 * @return
	 */
	@GetMapping("/users")
	public List<User> retrieveAllUsers();

	/**
	 * Get a user with the specified id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/users/{id}")
	public User retrieveUser(@PathVariable("id") int id);

	/**
	 * Create a user
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping("/users")
	public Resource<User> createUser(@Valid @RequestBody User user);

	/**
	 * Delete a user
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/users/{id}")
	public String deleteUser(@PathVariable("id") int id);

	/**
	 * Say greeting depends on the language sent
	 * 
	 * @param locale
	 * @return
	 */
	@GetMapping("/sayGreeting")
	public String sayGreeting(@RequestHeader(name = "Accept-Language", required = false) Locale locale);
}
