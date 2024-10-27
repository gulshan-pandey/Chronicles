package com.app.Chronicles.controller;

import com.app.Chronicles.entity.User;
import com.app.Chronicles.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @GetMapping("healthCheck")
    public String healthCheck(){
        return "OK";
    }

    @PostMapping("/create-user")
    public void createUser(@RequestBody User user){
        userService.saveNewEntry(user);
    }

}
