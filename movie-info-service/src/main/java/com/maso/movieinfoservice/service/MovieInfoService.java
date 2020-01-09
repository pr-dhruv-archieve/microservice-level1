package com.maso.movieinfoservice.service;

import com.maso.movieinfoservice.model.Movie;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class MovieInfoService {

    private List<Movie> movieList = Arrays.asList(
            new Movie("1", "Movie 1 Name", "Movie 1 Description"),
            new Movie("2", "Movie 2 Name", "Movie 2 Description"),
            new Movie("3", "Movie 3 Name", "Movie 3 Description"),
            new Movie("4", "Movie 4 Name", "Movie 4 Description")
    );

    public List<Movie> getAllMovieDetails() {
        return movieList;
    }

    public Movie getMovieById(String movieId) {
        Optional<Movie> movieDetails = movieList.stream()
                .filter(movie -> movie.getMovieId().equalsIgnoreCase(movieId))
                .findFirst();
        return movieDetails.orElse(null);
    }

}
