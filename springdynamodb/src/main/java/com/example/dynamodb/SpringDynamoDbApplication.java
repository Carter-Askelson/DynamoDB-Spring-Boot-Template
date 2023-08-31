package com.example.dynamodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import org.springframework.context.ConfigurableApplicationContext;
import com.example.dynamodb.service.MovieSearchService;
import com.example.dto.Movie;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SpringDynamoDbApplication {

	public static void main(String[] args) {
		//System.out.println("Test");
		//SpringApplication.run(SpringDynamoDbApplication.class, args);
		ConfigurableApplicationContext context = SpringApplication.run(SpringDynamoDbApplication.class, args);

        // Fetch the MovieSearchService bean
        MovieSearchService movieSearchService = context.getBean(MovieSearchService.class);

        // Fetch all movies and print them
        System.out.println("All Movies:");
        movieSearchService.findAllMovies().forEach(movie -> System.out.println(movie.toString()));
	}

}
