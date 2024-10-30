package com.app.Chronicles.service;

import com.app.Chronicles.entity.JournalEntry;
import com.app.Chronicles.repository.UserRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;




public class UserServiceImplTest {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
   private UserRepo userRepo;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }
    com.app.Chronicles.entity.User appUser = com.app.Chronicles.entity.User.builder()
            .username("user")
            .password("pass")
            .roles(new ArrayList<>())  // or whatever roles structure you have
            .build();

    @Test
    public void loadUserByUsername(){
        when(userRepo.findByUsername(ArgumentMatchers.anyString())).thenReturn(appUser);
        UserDetails user = userDetailsService.loadUserByUsername("user");
        Assertions.assertNotNull(user);
    }
}
