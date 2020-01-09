package com.maso.ratingdataservice.service;

import com.maso.ratingdataservice.model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RatingService {

    private List<Rating> ratingList = Arrays.asList(
            new Rating("1",8.9),
            new Rating("2",7.8),
            new Rating("3",8.6),
            new Rating("4",0)
    );

    public List<Rating> getAllRatedMovies() {
        return ratingList;
    }

    public Rating getMovieRatingById(String movieId) {
        Optional<Rating> movieRating = ratingList.stream()
                .filter(rating -> rating.getMovieId().equalsIgnoreCase(movieId))
                .findFirst();
        return movieRating.orElse(null);

    }

}
