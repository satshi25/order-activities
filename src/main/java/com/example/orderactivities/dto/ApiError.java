package com.example.orderactivities.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;


public class ApiError {
    @JsonProperty(value = "error")
    private String error;

    public ApiError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
