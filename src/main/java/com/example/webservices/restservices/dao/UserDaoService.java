package com.example.webservices.restservices.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.webservices.restservices.model.User;

@Component
public class UserDaoService {
	private static List<User> users = new ArrayList<>();
	private static int usersCount = 2;

	static {
		users.add(new User(1, "Alexis", new Date()));
		users.add(new User(2, "Carolina", new Date()));
	}

	public List<User> findAll() {
		return users;
	}

	public User save(User user) {

		if (user.getId() == null) {
			user.setId(usersCount++);
		}
		users.add(user);
		return user;
	}

	public User findOne(int id) {
		for (User us : users) {
			if (us.getId() == id) {
				return us;
			}
		}
		return null;
	}

}
