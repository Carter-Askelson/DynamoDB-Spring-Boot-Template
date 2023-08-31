package com.example.dynamodb.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;
import com.example.dto.Movie;
import com.example.dynamodb.repositories.MovieRepository;

@Service
public class MovieSearchService {

	public MovieRepository movieRepository;
	
	
	
	public MovieSearchService(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}
	
	
	public List<Movie> findAllMovies() {

		return StreamSupport.stream(movieRepository.findAll().spliterator(), true).collect(Collectors.toList());
		
	}

}
