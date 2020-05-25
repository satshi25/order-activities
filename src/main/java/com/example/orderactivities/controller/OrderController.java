package com.example.orderactivities.controller;

import com.example.orderactivities.entity.OrderRequest;
import com.example.orderactivities.entity.OrderResponse;
import com.example.orderactivities.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping(value = "/orders")
    public ResponseEntity<OrderResponse> createOrders(@Valid @RequestBody OrderRequest orderRequest) {
        return ResponseEntity.ok(orderService.processOrderRequest(orderRequest));
    }

    @PatchMapping(value = "/orders/{id}")
    public ResponseEntity<String> takeOrders(@Valid @RequestBody String status, @PathVariable String id) {
        return null;
    }
}
