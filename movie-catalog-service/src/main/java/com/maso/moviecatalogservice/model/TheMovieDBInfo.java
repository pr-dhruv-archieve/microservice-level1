package com.maso.moviecatalogservice.model;

public class TheMovieDBInfo {

    private int id;
    private String original_title;
    private String overview;

    public TheMovieDBInfo() {
        super();
    }

    public TheMovieDBInfo(int id, String original_title, String overview) {
        this.id = id;
        this.original_title = original_title;
        this.overview = overview;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
}
