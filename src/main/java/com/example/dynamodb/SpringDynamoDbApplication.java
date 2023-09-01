package com.example.dynamodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import org.springframework.context.ConfigurableApplicationContext;

import com.example.dynamodb.config.DynamoDBConfig;
import com.example.dynamodb.service.MovieSearchService;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.example.dto.Movie;

import java.util.Scanner;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SpringDynamoDbApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringDynamoDbApplication.class, args);

        // Fetch the MovieSearchService bean
        MovieSearchService movieSearchService = context.getBean(MovieSearchService.class);

        DynamoDB dynamoDB = new DynamoDB(DynamoDBConfig.amazonDynamoDB(""));
        Table table = dynamoDB.getTable("movie");

        Scanner scanner = new Scanner(System.in);
        int choice;
        boolean movieExists = false;
        do {
            System.out.println("Select an option:");
            System.out.println("1. Add movie");
            System.out.println("2. Delete movie");
            System.out.println("3. Print movie list");
            System.out.println("4. Exit");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    if (!movieExists) {
                    	movieExists = true;
                        System.out.println("Movie added.");
                    } else {
                        System.out.println("Movie already added.");
                    }
                    break;
                case 2:
                    if (movieExists) {
                    	movieExists = false;
                        System.out.println("Movie deleted.");
                    } else {
                        System.out.println("Movie doesn't exist.");
                    }
                    break;
                case 3:
                    System.out.println("All Movies:");
                    movieSearchService.findAllMovies().forEach(movie -> System.out.println(movie.toString()));
                    break;
                case 4:
                    System.out.println("Exiting...");
                    SpringApplication.exit(context);
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
                    break;
            }

        } while (choice != 4);

        scanner.close();
    }
}
