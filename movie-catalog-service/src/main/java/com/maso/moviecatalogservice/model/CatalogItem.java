package com.maso.moviecatalogservice.model;

public class CatalogItem {

    private String movieName;
    private String movieDescription;
    private double movieRating;

    public CatalogItem() {
        super();
    }

    public CatalogItem(String movieName, String movieDescription, double movieRating) {
        this.movieName = movieName;
        this.movieDescription = movieDescription;
        this.movieRating = movieRating;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieDescription() {
        return movieDescription;
    }

    public void setMovieDescription(String movieDescription) {
        this.movieDescription = movieDescription;
    }

    public double getMovieRating() {
        return movieRating;
    }

    public void setMovieRating(double movieRating) {
        this.movieRating = movieRating;
    }
}
