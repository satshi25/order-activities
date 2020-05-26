package com.example.orderactivities.exception;

public class NoResultStatusException extends Exception{
    private String message;

    public NoResultStatusException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
