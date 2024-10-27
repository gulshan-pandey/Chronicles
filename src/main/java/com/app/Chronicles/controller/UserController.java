package com.app.Chronicles.controller;


import com.app.Chronicles.entity.User;
import com.app.Chronicles.repository.UserRepo;
import com.app.Chronicles.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @Autowired
    private UserRepo userRepo;


    @GetMapping
    public List<User> getAllUsers(){
        return userService.findAll();
    }



    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        //the security context holder will give us the current authenticated user from the SpringConfig file
        //whenever the user is authenticated, its details gets stored in security context holder
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User userInDb = userService.findByUsername(username);
//        if(userInDb!=null){               // now this will never be null bcz its comming form the authenticated user
            userInDb.setUsername(user.getUsername());
            userInDb.setPassword(user.getPassword());             // password will be stored in the db in hashed format(encrypted)
            userService.saveNewEntry(userInDb);
//        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepo.deleteByUsername(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}


