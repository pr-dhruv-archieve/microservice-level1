package com.maso.ratingdataservice.model;

public class TheMovieDBRating {
    private int id;
    private double vote_average;

    public TheMovieDBRating() {
        super();
    }

    public TheMovieDBRating(int id, double vote_average) {
        this.id = id;
        this.vote_average = vote_average;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }
}
