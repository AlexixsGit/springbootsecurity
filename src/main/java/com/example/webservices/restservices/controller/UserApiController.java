package com.example.webservices.restservices.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.RestController;

import com.example.webservices.restservices.dao.UserDaoService;
import com.example.webservices.restservices.exceptions.UserNotFoundException;
import com.example.webservices.restservices.model.User;

@RestController
public class UserApiController implements UserApi {

	@Autowired
	private UserDaoService service;

	@Autowired
	private MessageSource messageSource;

	@Override
	public List<User> retrieveAllUsers() {
		return this.service.findAll();
	}

	@Override
	public User retrieveUser(int id) {
		User foundUser = this.service.findOne(id);

		if (foundUser == null) {
			throw new UserNotFoundException("User " + id + " not found");
		}
		return foundUser;
	}

	@Override
	public Resource<User> createUser(User user) {
		User newUser = this.service.save(user);

		Resource<User> resource = new Resource<User>(newUser);

		ControllerLinkBuilder link = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());

		resource.add(link.withRel("users"));

		return resource;
	}

	@Override
	public String deleteUser(int id) {
		User deletedUser = this.service.deleteById(id);

		if (deletedUser == null) {
			throw new UserNotFoundException("User " + id + " not found");
		}
		return "User deleted successfuly";
	}

	@Override
	public String sayGreeting(Locale locale) {
		return messageSource.getMessage("application.start.greeting", null, locale);

	}

}
