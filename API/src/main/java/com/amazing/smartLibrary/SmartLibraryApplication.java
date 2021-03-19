package com.amazing.smartLibrary;

import com.mongodb.client.MongoClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
public class SmartLibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartLibraryApplication.class, args);
	}

	@Bean
	public MongoOperations getMongoOperations() {
		return new MongoTemplate(MongoClients.create(), "smartLibrary");
	}

}
