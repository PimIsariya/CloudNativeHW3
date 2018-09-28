package com.sit.cloudnative.user;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserAdapter {

    public User getUserDetail(int userId){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8081/user/"+userId;
        User user = restTemplate.getForObject(url, User.class);
        return user;
    }

    // public User[] getAllUser(){
    //     RestTemplate restTemplate = new RestTemplate();
    //     String url = "http://localhost:8081/user";
    //     User[] users = restTemplate.getForObject(url, User[].class);
    //     return users;
    // }

    // public User createUser(User user){
    //     RestTemplate restTemplate = new RestTemplate();
    //     String url = "http://localhost:8081/user";
    //     User user = restTemplate.getForObject(url, User.class);
    //     return user;
    // }
    
}