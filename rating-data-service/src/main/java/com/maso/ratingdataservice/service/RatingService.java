package com.maso.ratingdataservice.service;

import com.maso.ratingdataservice.model.Rating;
import com.maso.ratingdataservice.model.TheMovieDBRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RatingService {

    @Value("${api.key}")
    private String apiKey;

    @Autowired
    private WebClient.Builder webClientBuilder;

    private List<Rating> ratingList = Arrays.asList(
            new Rating("1", 8.9),
            new Rating("2", 7.8),
            new Rating("3", 8.6),
            new Rating("4", 0)
    );

    private List<String> movieIds = Arrays.asList(new String[]{"101", "102", "103", "104", "105", "106", "107", "108", "109", "110"});

    public List<Rating> getAllRatedMovies() {
        return ratingList;
    }

    public Rating getMovieRatingById(String movieId) {
        Optional<Rating> movieRating = ratingList.stream()
                .filter(rating -> rating.getMovieId().equalsIgnoreCase(movieId))
                .findFirst();
        return movieRating.orElse(null);

    }

    public List<TheMovieDBRating> getAllRatedMovieFromMovieDB() {
        return movieIds.stream()
                .map(movieId -> {
                    TheMovieDBRating movieDBRating = webClientBuilder.build()
                            .get()
                            .uri("https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + apiKey)
                            .retrieve()
                            .bodyToMono(TheMovieDBRating.class)
                            .block();
                    return movieDBRating;
                }).collect(Collectors.toList());
    }

    public TheMovieDBRating getRatedMovieByIdFromMovieDB(String inputMovieID) {
        Optional<TheMovieDBRating> movie = movieIds.stream()
                .filter(id -> id.equalsIgnoreCase(inputMovieID))
                .map(id -> {
                    return webClientBuilder.build()
                            .get()
                            .uri("https://api.themoviedb.org/3/movie/" + id + "?api_key=" + apiKey)
                            .retrieve()
                            .bodyToMono(TheMovieDBRating.class)
                            .block();
                })
                .findFirst();

        return movie.orElse(null);
    }

}
