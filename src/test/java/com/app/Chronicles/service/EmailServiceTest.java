package com.app.Chronicles.service;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {

    @Autowired
    EmailService emailService;


    @Test
    void testMail(){
        emailService.sendEmail("abc@gmail.com","from Backend","Ignore this mail as it is for testing only");
    }


}
