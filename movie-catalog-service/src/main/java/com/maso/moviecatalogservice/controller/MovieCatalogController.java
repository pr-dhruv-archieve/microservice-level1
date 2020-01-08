package com.maso.moviecatalogservice.controller;

import com.maso.moviecatalogservice.model.CatalogItem;
import com.maso.moviecatalogservice.model.Movie;
import com.maso.moviecatalogservice.model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {

    @Autowired
    private WebClient.Builder webClientBuilder;

    // Get all rated movieId
    @RequestMapping("/{movieId}")
    public List<CatalogItem> getMovieCatalog(@PathVariable("movieId") String movieId) {

        // Step 1 : Get all rated movie data
        List<Rating> movieRatings = Arrays.asList(new Rating("1", 4.5), new Rating("2", 8.9));

        // Step 2 : For each movieId, call the movie-info-service and get details
        // Step 3 : Put them all together and return
        return movieRatings.stream().map(
                rating -> {
                        // Making asynchronous call to movie-info-service microservice
                        // Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId()  , Movie.class);
                    Movie movie = webClientBuilder.build()  // Here, we are creating instance of the client to make a call
                        .get()  // Name of the method, it can be PUT, DELETE, POST, GET etc
                        .uri("http://localhost:8082/movies/" + rating.getMovieId())  // URL to call
                        .retrieve() // Receiving the data from the URL response
                        /**
                         * Target class name, Whatever JSON we are receiving convert that into the class.
                         * Mono means we are going to get the object after some time not right away. You are going to get it eventually.
                         * Simple terms, It returns the blank container and fill the data when it is available.
                         *
                         * Another thing, Since we are blocking the execution and using mono Object, so this API getMovieCatalog() is called
                         * it is going to return the a mono object(blank container) and fill the data when it is available.
                         * This is approach is technically available but we are not using this approach.
                         * Here, we are returning the List<CatalogItem> so this method will wait until the data is available.
                         */
                        .bodyToMono(Movie.class)
                        .block();   // Block the execution till the data is fetched.

                    return new CatalogItem(rating.getMovieId(), movie.getMovieName(), "Movie id " + rating.getMovieId() + " desc", rating.getRating());
                }
        ).collect(Collectors.toList());




    }

}
