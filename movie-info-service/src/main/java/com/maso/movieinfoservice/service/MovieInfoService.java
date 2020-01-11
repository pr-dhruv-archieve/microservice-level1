package com.maso.movieinfoservice.service;

import com.maso.movieinfoservice.model.Movie;
import com.maso.movieinfoservice.model.TheMovieDBInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieInfoService {

    @Value("${api.key}")
    private String apiKey;

    @Autowired
    private WebClient.Builder webClientBuilder;

    private List<Movie> movieList = Arrays.asList(
            new Movie("1", "Movie 1 Name", "Movie 1 Description"),
            new Movie("2", "Movie 2 Name", "Movie 2 Description"),
            new Movie("3", "Movie 3 Name", "Movie 3 Description"),
            new Movie("4", "Movie 4 Name", "Movie 4 Description")
    );

    private List<String> movieIds = Arrays.asList(new String[]{"101", "102", "103", "104", "105", "106", "107", "108", "109", "110"});

    public List<Movie> getAllMovieDetails() {
        return movieList;
    }

    public Movie getMovieById(String movieId) {
        Optional<Movie> movieDetails = movieList.stream()
                .filter(movie -> movie.getMovieId().equalsIgnoreCase(movieId))
                .findFirst();
        return movieDetails.orElse(null);
    }

    public List<TheMovieDBInfo> getMovieFromMovieDB() {
        return movieIds.stream()
                .map(movieId -> {
                    TheMovieDBInfo movieDBRating = webClientBuilder.build()
                            .get()
                            .uri("https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + apiKey)
                            .retrieve()
                            .bodyToMono(TheMovieDBInfo.class)
                            .block();
                    return movieDBRating;
                }).collect(Collectors.toList());
    }

    public TheMovieDBInfo getMovieByIdFromMovieDB(String inputMovieID) {
        Optional<TheMovieDBInfo> movie = movieIds.stream()
                .filter(id -> id.equalsIgnoreCase(inputMovieID))
                .map(id -> {
                    return webClientBuilder.build()
                            .get()
                            .uri("https://api.themoviedb.org/3/movie/" + id + "?api_key=" + apiKey)
                            .retrieve()
                            .bodyToMono(TheMovieDBInfo.class)
                            .block();
                })
                .findFirst();

        return movie.orElse(null);
    }

}
