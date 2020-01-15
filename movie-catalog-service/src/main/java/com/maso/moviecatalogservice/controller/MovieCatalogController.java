package com.maso.moviecatalogservice.controller;

import com.maso.moviecatalogservice.model.CatalogItem;
import com.maso.moviecatalogservice.model.Movie;
import com.maso.moviecatalogservice.model.Rating;
import com.maso.moviecatalogservice.service.CatalogService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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
    private CatalogService catalogService;

    /**
     * Get movie details by ID
     *
     * @return
     */
    @RequestMapping("/all")
    @HystrixCommand
    public List<CatalogItem> getAllMovies() {
        return catalogService.getAllMovies();
    }

    @RequestMapping("/{movieId}")
    @HystrixCommand
    public CatalogItem getMovieById(@PathVariable("movieId") String movieId) {
        return catalogService.getMovieById(movieId);
    }

    @RequestMapping("/theMovieDB/all")
    public List<CatalogItem> getAllMoviesFromMovieDB() {
        return catalogService.getAllMoviesFromMovieDB();
    }

    @RequestMapping("/theMovieDB/{movieId}")
    public CatalogItem getMovieByIdFromMovieDB(@PathVariable("movieId") String movieId) {
        return catalogService.getMovieByIdFromMovieDB(movieId);
    }
}
