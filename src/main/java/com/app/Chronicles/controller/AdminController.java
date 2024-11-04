package com.app.Chronicles.controller;


import com.app.Chronicles.cache.AppCache;
import com.app.Chronicles.entity.User;
import com.app.Chronicles.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    AppCache appCache;


    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers(){
        List<User> all = userService.findAll();
        if(!all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create-admin")                   // only the Existing admin can create new admin , bcz it has auth on its api
    public ResponseEntity<?> createAdmin(@RequestBody User user){
        return new ResponseEntity<>(userService.createAdmin(user), HttpStatus.CREATED);
    }

    @GetMapping("/clear-cache")
    public ResponseEntity<?> clearCache(){
        appCache.init();
        return new ResponseEntity<>(HttpStatus.OK);
    }




}
