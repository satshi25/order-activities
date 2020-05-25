package com.example.orderactivities.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DistanceMatrixResponse {
    @SerializedName("destination_address")
    private List<String> destination_address;

    @SerializedName("origin_address")
    private List<String> origin_address;

    @SerializedName("rows")
    private List<Elements> rows;

    @SerializedName("status")
    private String status;

    public List<String> getDestination_address() {
        return destination_address;
    }

    public void setDestination_address(List<String> destination_address) {
        this.destination_address = destination_address;
    }

    public List<String> getOrigin_address() {
        return origin_address;
    }

    public void setOrigin_address(List<String> origin_address) {
        this.origin_address = origin_address;
    }

    public List<Elements> getRows() {
        return rows;
    }

    public void setRows(List<Elements> rows) {
        this.rows = rows;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
