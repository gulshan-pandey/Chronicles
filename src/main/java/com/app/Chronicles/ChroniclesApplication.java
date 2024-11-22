package com.app.Chronicles;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableTransactionManagement			// this allows you to use @Transactional annotation
@EnableScheduling
public class ChroniclesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChroniclesApplication.class, args);
	}


	@Bean			// we have to have the instance of PlatformTransactionManager to make the connection with db to perform the transactions
	public PlatformTransactionManager go(MongoDatabaseFactory dbFactory){
		return new MongoTransactionManager(dbFactory);
	}

	//MongoDatabaseFactory is responsible for making the connection with db, helps to make sessions, MongoTransactionManager also implements PlatformTransactionManager


	@Bean
	RestTemplate restTemplate(){
		return new RestTemplate();
	}

}
