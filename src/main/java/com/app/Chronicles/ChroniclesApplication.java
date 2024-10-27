package com.app.Chronicles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class ChroniclesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChroniclesApplication.class, args);
	}


	@Bean			// we have to have the instance of PlatformTransactionManager to make the connection with db to perform the transactions
	public PlatformTransactionManager go(MongoDatabaseFactory dbFactory){
		return new MongoTransactionManager(dbFactory);
	}

	//MongoDatabaseFactory is responsible for making the connection with db, helps to make sessions, MongoTransactionManager also implements PlatformTransactionManager
}
