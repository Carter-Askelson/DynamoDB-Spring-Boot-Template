package com.example.dynamodb.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.DeleteItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.KeyAttribute;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.DeleteItemRequest;
import com.example.dto.Movie;
import com.example.dynamodb.config.DynamoDBConfig;
import com.example.dynamodb.repositories.MovieRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class MovieSearchService {
	
	@Autowired
	private AmazonDynamoDB amazondynamoDB;

	public MovieRepository movieRepository;
	
	
	
	public MovieSearchService(MovieRepository MovieRepository) {
		this.movieRepository = MovieRepository;
	}
	
	
	
	
	public List<Movie> findAllMovies() {

		return StreamSupport.stream(movieRepository.findAll().spliterator(), true).collect(Collectors.toList());
		
	}
	
	/*
	 * public void addNewMovieThroughDynamo() { DynamoDB dynamoDB = new
	 * DynamoDB(amazondynamoDB); Table table = dynamoDB.getTable("movie");
	 * 
	 * String title = "Star Wars";
	 * 
	 * try { System.out.println("Starting the insert process"); PutItemOutcome
	 * outcome = table.putItem(new Item().withPrimaryKey("filmId", "2"))
	 * .with("ReleaseYear", 1977).with("Plot", "Good vs Evil").with("Rated", "PG");
	 * System.out.println("Successfully inserted"); } catch (Exception ex) {
	 * ex.printStackTrace(); } }
	 */
	public void createNewMovie(Table table) {
		List<String> genres = new ArrayList<String>();
		genres.add("Action");
		genres.add("Adventure");
		genres.add("Fantasy");
		Item item = new Item()
			    .withPrimaryKey("FilmId", "2")
			    .withNumber("ReleaseYear", 1977)
			    .withString("Awards", "Won 7 Oscars. Another 59 wins & 36 Nominations")
			    .withString("Country", "USA")
			    .withList("Genre", genres)
			    .withString("imdbRating", "8.6")
			    .withString("Language", "English")
			    .withString("Plot", "Good vs Evil")
			    .withString("Rated", "PG")
			    .withString("Released", "25 May 1977")
			    .withString("Runtime", "2 Hrs & 1 Min")
			    .withString("Title", "Star Wars Episode 4: A New Hope");
				
			// Write the item to the table
			PutItemOutcome outcome = table.putItem(item);
	}
	
	
	public void deleteNewMovie(Table table) {
		 table.deleteItem("FilmId", "2", "ReleaseYear", 1977);
    }
	
}
	//public void addNewMovie(Movie movie) {
	//	movieRepository.save(movie);
	//}
	
	//public Movie addNewMovie(Movie movie) {
	//	return MovieRepository.save(movie);
	//}


