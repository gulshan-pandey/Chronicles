package com.app.Chronicles.service;

import com.app.Chronicles.entity.User;
import com.app.Chronicles.repository.UserRepo;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest                 //to start the Spring App so that we can inject dependencies and test them
public class UserServiceTests {


    @Autowired
    UserRepo userRepo;


    @Disabled
    @Test
    public void testFindByUsername(){
//        assertEquals(4,2+2);
//        assertNotNull(userRepo.findByUsername("user"));
        User s = userRepo.findByUsername("r");
        assertTrue(!s.getJournalEntries().isEmpty());
    }


    @ParameterizedTest
    @ValueSource(strings={                    // can be      @CsvSource({...  as well
            "1,2,3",
            "2,3,5",
            "3,4,10"
    })
    public void test(int a, int b, int expected){
        assertEquals(expected,a+b);
    }


    @ParameterizedTest
    @CsvSource({
            "s",
            "user",
            "golu"
    })
    public void test2(String name){
        assertNotNull(userRepo.findByUsername(name),"User not found with username : " + name);
    }
}
