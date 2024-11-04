package com.app.Chronicles.controller;


import com.app.Chronicles.api.response.Quotes;
import com.app.Chronicles.api.response.Weather;
import com.app.Chronicles.entity.User;
import com.app.Chronicles.repository.UserRepo;
import com.app.Chronicles.service.QuoteService;
import com.app.Chronicles.service.UserService;
import com.app.Chronicles.service.WeatherService;
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


    @Autowired
    private WeatherService weatherService;

    @Autowired
    private QuoteService quoteService;


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
            boolean isSaved = userService.saveNewEntry(userInDb);
//        }
        return new ResponseEntity<>(isSaved,HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepo.deleteByUsername(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping()
    public ResponseEntity<?> greet(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Weather weather = weatherService.getWeather("New Delhi");
        String greeting = " ";
        if(greeting != null){
            greeting ="!, Weather feels like " + weather.getCurrent().getFeelslike() + " degree";
        }

        Quotes quote = quoteService.getQuote();
        String quoteString =" ";
        if(quote != null){
            quoteString = quote.getContent();
        }



        return new ResponseEntity<>( quoteString + " \n hii, " + authentication.getName()  + greeting   ,HttpStatus.OK);
    }


}


