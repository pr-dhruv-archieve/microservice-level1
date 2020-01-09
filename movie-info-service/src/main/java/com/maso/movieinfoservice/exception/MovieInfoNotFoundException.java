package com.maso.movieinfoservice.exception;

public class MovieInfoNotFoundException extends Exception {

    public MovieInfoNotFoundException() {
        super();
    }

    public MovieInfoNotFoundException(String message) {
        super(message);
    }
}
