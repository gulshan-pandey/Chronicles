package com.app.Chronicles.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisService {

    @Autowired
    @Qualifier("redisTemplateProvider")
    RedisTemplate redisTemplate;


    public <T> T get(String key, Class <T> entityClass){
        try{

            Object o = redisTemplate.opsForValue().get(key);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(o.toString(),entityClass);      // method to deserialize JSON content into Java objects
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }

    public void set(String key, Object o , Long ttl){           // for time bounded cache
        try{
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(o);     // this is used to transform a Java object into a JSON
            redisTemplate.opsForValue().set(key,json,ttl, TimeUnit.DAYS);

        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    public void set(String key, Object o) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(o); // Convert the Java object to JSON
            redisTemplate.opsForValue().set(key, json); // Store in Redis without TTL
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}
