package com.example.orderactivities.controller;

import com.example.orderactivities.custom.NumberValueConstraint;
import com.example.orderactivities.dto.OrderRequest;
import com.example.orderactivities.dto.OrderResponse;
import com.example.orderactivities.dto.TakeOrder;
import com.example.orderactivities.exception.NoResultStatusException;
import com.example.orderactivities.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/orders")
@Validated
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrders(@Valid @RequestBody OrderRequest orderRequest) throws NoResultStatusException {
        return ResponseEntity.ok(orderService.processOrderRequest(orderRequest));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TakeOrder> takeOrders(@Valid @RequestBody String status, @PathVariable String id) {
        TakeOrder takeOrder = new TakeOrder(orderService.processTakeOrderRequest(Integer.parseInt(id), status));
        return ResponseEntity.ok(takeOrder);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getOrderList(@Valid @NumberValueConstraint @RequestParam(value = "page") Integer page,
                                                            @Valid @NumberValueConstraint @RequestParam(value = "limit") Integer limit) {
        return ResponseEntity.ok(orderService.processOrderList(page, limit));
    }
}
