package com.example.webservices.restservices.controller;

import com.example.webservices.restservices.dao.UserDaoService;
import com.example.webservices.restservices.exceptions.UserNotFoundException;
import com.example.webservices.restservices.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserDaoService service;

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return this.service.findAll();
    }

    @GetMapping("/users/{id}")
    public Optional<User> retrieveUser(@PathVariable("id") Long id) {
        Optional<User> foundUser = this.service.findOne(id);

        if (!foundUser.isPresent()) {
            throw new UserNotFoundException("User " + id + " not found");
        }
        return foundUser;
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User newUser = this.service.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();

    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable("id") Long id) {

        if (this.service.deleteById(id)) {
            return "User deleted successfully";
        } else {
            return "User could not be deleted";
        }
    }

}
