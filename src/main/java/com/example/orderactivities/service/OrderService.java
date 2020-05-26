package com.example.orderactivities.service;

import com.example.orderactivities.dto.OrderRequest;
import com.example.orderactivities.dto.OrderResponse;
import com.example.orderactivities.exception.NoResultStatusException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    public OrderResponse processOrderRequest(OrderRequest request) throws NoResultStatusException;
    public String processTakeOrderRequest(Integer id, String status);
    public List<OrderResponse> processOrderList(Integer page, Integer limit);
}
