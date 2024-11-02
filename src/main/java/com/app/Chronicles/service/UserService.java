package com.app.Chronicles.service;

import com.app.Chronicles.entity.User;
import com.app.Chronicles.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;




@Service
@Slf4j              // magic of lombok...♨️... it will inject the getter and setter during compile time
public class UserService {

//    private static final Logger log = LoggerFactory.getLogger(UserService.class);             // this can be replaced by @Slf4j on the class


    @Autowired
   private UserRepo userRepo;

    private static final PasswordEncoder encoder = new BCryptPasswordEncoder();


    public void saveEntry(User user) {
            userRepo.save(user);
    }


    public boolean saveNewEntry(User user) {
        try {
            user.setPassword(encoder.encode(user.getPassword()));
            user.setRoles(List.of("USER"));
            userRepo.save(user);
            return true;
        } catch (Exception e) {
            log.info("\n\n logger----Error occurred {} {} ",user.getUsername(), e.getMessage());
            log.warn("error was here");
            log.error("error in error");
            log.debug(" DEBUG: this will not work by default");
            log.trace("this will not work by default");
            return false;
        }
    }

    public List<User> findAll(){
        return userRepo.findAll();
    }

    public Optional<User> findById(ObjectId id){
        return userRepo.findById(id);
    }

    public void deleteById(ObjectId id){
        userRepo.deleteById(id);
    }

    public User findByUsername(String username){
       return userRepo.findByUsername(username);
    }


    public User createAdmin(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
       return userRepo.save(user);
    }
}
