package com.app.Chronicles.repository;

import com.app.Chronicles.entity.User;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserRepoImplTest {

    @Autowired
    UserRepoImpl userRepoImpl;

    @Test
    public void testFindByQuery(){
         userRepoImpl.getUserForSA();
    }

}
