package com.maso.moviecatalogservice.controller;

import com.maso.moviecatalogservice.model.CatalogItem;
import com.maso.moviecatalogservice.model.Movie;
import com.maso.moviecatalogservice.model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {

    @Autowired
    private RestTemplate restTemplate;

    // Get all rated movieId
    @RequestMapping("/{movieId}")
    public List<CatalogItem> getMovieById(@PathVariable("movieId") String movieId) {

        // Step 1 : Get all rated movie data
        List<Rating> movieRatings = Arrays.asList(new Rating("1", 4.5), new Rating("2", 8.9));

        // Step 2 : For each movieId, call the movie-info-service and get details
        // Step 3 : Put them all together and return
        return movieRatings.stream().map(
                rating -> {
                        // Making synchronous call to movie-info-service microservice
                        Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId()  , Movie.class);
                        return new CatalogItem(rating.getMovieId(), movie.getMovieName(), "Movie id " + rating.getMovieId() + " desc", rating.getRating());
                }
        ).collect(Collectors.toList());




    }

}
