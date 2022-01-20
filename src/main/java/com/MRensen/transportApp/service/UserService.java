package com.MRensen.transportApp.service;

import com.MRensen.transportApp.model.User;
import com.MRensen.transportApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User getByUsername(String username){
        return userRepository.getById(username);
    }
}
