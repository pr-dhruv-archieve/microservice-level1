package com.maso.ratingdataservice.controller;

import com.maso.ratingdataservice.exception.RatingNotFoundException;
import com.maso.ratingdataservice.model.Rating;
import com.maso.ratingdataservice.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rating")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @RequestMapping("/all")
    public List<Rating> getAllRatedMovie() {
        return ratingService.getAllRatedMovies();
    }

    @RequestMapping("/{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId) {
        try {
            Rating rating = ratingService.getMovieRatingById(movieId);
            if(rating == null)
                throw new RatingNotFoundException("Rating not found for the movieId : " + movieId);
            return rating;
        }catch (RatingNotFoundException rnfe) {
            return new Rating(null,-1);
        }

    }

}
