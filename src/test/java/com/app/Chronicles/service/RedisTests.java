package com.app.Chronicles.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class RedisTests {

    @Autowired
    @Qualifier("redisTemplateProvider")  // Ensure correct bean is used
    RedisTemplate redisTemplate;

    @Test
    public void redistest(){
       redisTemplate.opsForValue().set("email","abc@gmail.com");

        Object salary = redisTemplate.opsForValue().get("salary");            // by running this in debugger mode confirms that the connection has established with redis

        System.out.println("salary = " + salary);
    }
}
