package com.maso.ratingdataservice.exception;

public class RatingNotFoundException extends Exception {
    public RatingNotFoundException() {
        super();
    }

    public RatingNotFoundException(String message) {
        super(message);
    }
}
