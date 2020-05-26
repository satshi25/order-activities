package com.example.orderactivities.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;

public class TakeOrder {
    @JsonProperty(value = "status")
    @NotEmpty(message = "status should have a value")
    String status;

    public TakeOrder(@NotEmpty(message = "status should have a value") String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
