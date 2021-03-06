package com.ourteam.kodi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class KodiApplication {

	public static void main(String[] args) {
		SpringApplication.run(KodiApplication.class, args);
	}

}
