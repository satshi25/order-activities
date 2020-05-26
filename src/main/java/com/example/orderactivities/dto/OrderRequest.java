package com.example.orderactivities.dto;

import com.example.orderactivities.custom.CoordinateConstraint;
import com.example.orderactivities.custom.StringValueConstraint;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class OrderRequest {

    @JsonProperty("origin")
    @Size(min = 2, max = 2, message = "origin can only have two string values")
    @StringValueConstraint
    @CoordinateConstraint
    List<String> origin;

    @JsonProperty("destination")
    @Size(min = 2, max = 2, message = "destination can only have two string values")
    @StringValueConstraint
    @CoordinateConstraint
    List<String> destination;

    public List<String> getDestination() {
        return destination;
    }

    public void setDestination(List<String> destination) {
        this.destination = destination;
    }

    public List<String> getOrigin() {
        return origin;
    }

    public void setOrigin(List<String> origin) {
        this.origin = origin;
    }
}
