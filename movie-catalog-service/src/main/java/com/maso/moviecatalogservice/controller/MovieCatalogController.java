package com.maso.moviecatalogservice.controller;

import com.maso.moviecatalogservice.model.CatalogItem;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {

    // Get all rated movieId
    @RequestMapping("/{movieId}")
    public List<CatalogItem> getMovieById(@PathVariable("movieId") String movieId) {

        // Step 1 : Get all rated movie data

        // Step 2 : For each movieId, call the movie-info-service and get details

        // Step 3 : Put them all together and return
        return
    }

}
