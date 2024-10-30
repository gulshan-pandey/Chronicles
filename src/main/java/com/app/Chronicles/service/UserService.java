package com.app.Chronicles.service;

import com.app.Chronicles.entity.User;
import com.app.Chronicles.repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;




@Service
public class UserService {


   @Autowired
   private UserRepo userRepo;

    private static final PasswordEncoder encoder = new BCryptPasswordEncoder();


    public void saveEntry(User user) {
            userRepo.save(user);
    }


    public boolean saveNewEntry(User user) {
        try {
            user.setPassword(encoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userRepo.save(user);
            return true;
        } catch (Exception e) {
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
