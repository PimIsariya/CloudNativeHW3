package com.sit.cloudnative.UserServiceForAdapter;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService{

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public User create(User user){
        return userRepository.save(user);
    }

    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }

    public void deleteUserById(int id){
        userRepository.deleteById(id);
    }
}