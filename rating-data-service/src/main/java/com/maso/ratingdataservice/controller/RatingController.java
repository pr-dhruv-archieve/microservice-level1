package com.maso.ratingdataservice.controller;

import com.maso.ratingdataservice.exception.RatingNotFoundException;
import com.maso.ratingdataservice.model.Rating;
import com.maso.ratingdataservice.model.TheMovieDBRating;
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
    public Rating getMovieRatingById(@PathVariable("movieId") String movieId) {
        try {
            Rating rating = ratingService.getMovieRatingById(movieId);
            if (rating == null)
                throw new RatingNotFoundException("Rating not found for the movieId : " + movieId);
            return rating;
        } catch (RatingNotFoundException rnfe) {
            return new Rating(null, -1);
        }

    }

    @RequestMapping("/theMovieDB/all")
    public List<TheMovieDBRating> getAllRatedMovieFromMovieDB() {
        return ratingService.getAllRatedMovieFromMovieDB();
    }

    @RequestMapping("/theMovieDB/{movieId}")
    public TheMovieDBRating getRatedMovieByIdFromMovieDB(@PathVariable("movieId") String movieId) {
        try {
            TheMovieDBRating movieDBRating = ratingService.getRatedMovieByIdFromMovieDB(movieId);
            if (movieDBRating == null)
                throw new RatingNotFoundException("Rating not found for the movieId : " + movieId);
            return movieDBRating;
        } catch (RatingNotFoundException rnfe) {
            return new TheMovieDBRating(-1, -1);
        }
    }

}
