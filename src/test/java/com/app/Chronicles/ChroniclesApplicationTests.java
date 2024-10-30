package com.app.Chronicles;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackages = "com.app.Chronicles.repository")
@SpringBootTest
class ChroniclesApplicationTests {

	@Test
	void contextLoads() {
	}

}
