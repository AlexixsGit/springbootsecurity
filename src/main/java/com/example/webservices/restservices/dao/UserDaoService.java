package com.example.webservices.restservices.dao;

import com.example.webservices.restservices.model.User;
import com.example.webservices.restservices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserDaoService {

    @Autowired
    private UserRepository userRepository;


    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public User save(User user) {
        return this.userRepository.save(user);
    }

    public Optional<User> findOne(Long id) {
        return this.userRepository.findById(id);
    }

    public boolean deleteById(Long id) {
        Optional<User> user = this.userRepository.findById(id);

        if (user.isPresent()) {
            this.userRepository.delete(user.get());
            return true;
        }
        return false;
    }

}
