package com.maso.movieinfoservice.controller;

import com.maso.movieinfoservice.exception.MovieInfoNotFoundException;
import com.maso.movieinfoservice.model.Movie;
import com.maso.movieinfoservice.model.TheMovieDBInfo;
import com.maso.movieinfoservice.service.MovieInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieInfoController {

    @Value("${api.key")
    private String apiKey;

    @Autowired
    private MovieInfoService movieInfoService;

    @RequestMapping("/all")
    public List<Movie> getAllMovies() {
        return movieInfoService.getAllMovieDetails();
    }

    @RequestMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
        try {
            Movie movie = movieInfoService.getMovieById(movieId);
            if (movie == null)
                throw new MovieInfoNotFoundException("Movie not found for the movieId : " + movieId);
            return movie;
        } catch (MovieInfoNotFoundException minfe) {
            return new Movie(null, null, null);
        }
    }

    @RequestMapping("/theMovieDB/all")
    public List<TheMovieDBInfo> getMovieFromMovieDB() {
        return movieInfoService.getMovieFromMovieDB();
    }

    @RequestMapping("/theMovieDB/{movieId}")
    public TheMovieDBInfo getMovieByIdFromMovieDB(@PathVariable("movieId") String movieId) {
        try {
            TheMovieDBInfo movieDBRating = movieInfoService.getMovieByIdFromMovieDB(movieId);
            if (movieDBRating == null)
                throw new MovieInfoNotFoundException("Movie not found for the movieId : " + movieId);
            return movieDBRating;
        } catch (MovieInfoNotFoundException minfe) {
            return new TheMovieDBInfo(-1, null, null);
        }
    }

}
