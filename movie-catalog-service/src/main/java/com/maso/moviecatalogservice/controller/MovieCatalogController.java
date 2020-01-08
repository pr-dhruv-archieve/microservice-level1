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

    @RequestMapping("/{movieId}")
    public List<CatalogItem> getMovieById(@PathVariable("movieId") String movieId) {
        return Collections.singletonList(new CatalogItem("Transformer", "Alien Robot movie", 7.5));
    }

}
