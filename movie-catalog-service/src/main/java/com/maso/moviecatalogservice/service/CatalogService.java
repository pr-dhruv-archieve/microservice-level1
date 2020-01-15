package com.maso.moviecatalogservice.service;

import com.maso.moviecatalogservice.model.*;
import com.netflix.discovery.converters.Auto;
import jdk.jfr.Unsigned;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CatalogService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;

    private List<String> requestedMovieId = Arrays.asList(new String("1"), new String("2"), new String("3"), new String("4"), new String("5"));
    private List<String> requestedMovieIdFromMovieDB = Arrays.asList(new String[]{"101", "102", "103", "104", "105", "106", "107", "108", "109", "110"});

    public List<CatalogItem> getAllMovies() {
        /** Step 1 : Get all rated movie data
         * Step 2 : For each movieId, call the movie-info-service and get details
         * Step 3 : Put them all together and return
         */
        // Using RestTemplate
/*        movieRatings.stream().map(
                rating -> {
                    // Making synchronous call to movie-info-service microservice
                    Movie movie = restTemplate.getForObject("http://movie-info-service/movie/" + rating.getMovieId()  , Movie.class);
                    return new CatalogItem(rating.getMovieId(), movie.getMovieName(), "Movie id " + rating.getMovieId() + " desc", rating.getRating());
                }
        ).collect(Collectors.toList()); */

        List<CatalogItem> catalog = requestedMovieId.stream()
                .map(movieId -> {
                    Movie movie = webClientBuilder.build()
                            .get()
                            .uri("http://movie-info-service/movie/" + movieId)
                            .retrieve()
                            .bodyToMono(Movie.class)
                            .block();
                    Rating rating = webClientBuilder.build()
                            .get()
                            .uri("http://rating-data-service/rating/" + movieId)
                            .retrieve()
                            .bodyToMono(Rating.class)
                            .block();
                    return new CatalogItem(movie.getMovieId(), movie.getMovieName(), movie.getMovieDescription(), rating.getRating());
                })
                .collect(Collectors.toList());

        catalog = catalog.stream()
                .filter(catalogItem -> catalogItem.getMovieId() != null)
                .collect(Collectors.toList());
        return catalog;

    }

    public CatalogItem getMovieById(String movieId) {
        Movie movie = webClientBuilder.build()
                .get()
                .uri("http://movie-info-service/movie/" + movieId)
                .retrieve()
                .bodyToMono(Movie.class)
                .block();
        Rating rating = webClientBuilder.build()
                .get()
                .uri("http://rating-data-service/rating/" + movieId)
                .retrieve()
                .bodyToMono(Rating.class)
                .block();
        if (movie.getMovieId() == null) {
            CatalogItem catalogItem = new CatalogItem();
            catalogItem.setMovieRating(-1);
            return catalogItem;
        }
        return new CatalogItem(movie.getMovieId(), movie.getMovieName(), movie.getMovieDescription(), rating.getRating());
    }

    public List<CatalogItem> getAllMoviesFromMovieDB() {
        return requestedMovieIdFromMovieDB.stream()
                .map(movieId -> getMovieByIdFromMovieDB(movieId))
                .collect(Collectors.toList());
    }

    public CatalogItem getMovieByIdFromMovieDB(String movieId) {

        //Using synchronous calls
        TheMovieDBInfo movie = restTemplate.getForObject("http://movie-info-service/movie/theMovieDB/" + movieId, TheMovieDBInfo.class);
        TheMovieDBRating rating = restTemplate.getForObject("http://rating-data-service/rating/theMovieDB/" + movieId, TheMovieDBRating.class);
        /* Using asynchronous calls
        TheMovieDBInfo movie = webClientBuilder.build()
                .get()
                .uri("http://movie-info-service/movie/theMovieDB/" + movieId)
                .retrieve()
                .bodyToMono(TheMovieDBInfo.class)
                .block();
        TheMovieDBRating rating = webClientBuilder.build()
                .get()
                .uri("http://rating-data-service/rating/theMovieDB/" + movieId)
                .retrieve()
                .bodyToMono(TheMovieDBRating.class)
                .block();
                */
        return new CatalogItem(movie.getId() + "", movie.getOriginal_title(), movie.getOverview(), rating.getVote_average());
    }
}
