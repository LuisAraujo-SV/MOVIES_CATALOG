package com.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@EnableJpaRepositories(value = "com.challenge.model.repositories")
@EntityScan(value = "com.challenge.model.entities")
public class MoviesCatalogApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoviesCatalogApiApplication.class, args);
	}

}
