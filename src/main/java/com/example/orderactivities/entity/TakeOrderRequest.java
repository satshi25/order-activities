package com.example.orderactivities.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;

public class TakeOrderRequest {
    @JsonProperty(value = "status")
    @NotEmpty(message = "status should have a value")
    String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
