package com.sit.cloudnative.UserServiceForAdapter;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController{

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @RequestMapping(value = "/alluser", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getUserList(){
        List<User> users = userService.getAllUser();
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User user_object = userService.create(user);
        return new ResponseEntity<User>(user_object, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<Optional<User>> getUser(@PathVariable("id") int id) {
        Optional<User> user = userService.getUserById(id);
        return new ResponseEntity<Optional<User>>(user, HttpStatus.OK);
    }

}