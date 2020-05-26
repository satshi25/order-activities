package com.example.orderactivities.dto;

import java.util.List;

public class OrderResponseList {

    List<OrderResponse> orders;

    public List<OrderResponse> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderResponse> orders) {
        this.orders = orders;
    }
}
