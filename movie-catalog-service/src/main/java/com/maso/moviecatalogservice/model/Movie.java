package com.maso.moviecatalogservice.model;

public class Movie {
    private String movieId;
    private String movieName;
    private String movieDescription;

    public Movie() {
        super();
    }

    public Movie(String movieId, String movieName, String movieDescription) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.movieDescription = movieDescription;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
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
}
