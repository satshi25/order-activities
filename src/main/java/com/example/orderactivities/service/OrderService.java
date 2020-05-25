package com.example.orderactivities.service;

import com.example.orderactivities.entity.DistanceMatrixResponse;
import com.example.orderactivities.entity.OrderRequest;
import com.example.orderactivities.entity.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    DistanceMatrixService distanceService;

    public OrderResponse processOrderRequest(OrderRequest request) {

        DistanceMatrixResponse result = distanceService.getDistance(request);

        OrderResponse response = new OrderResponse();
        response.setId("<orderid>");
        response.setDistance(result.getRows().get(0).getElements().get(0).getDistance().getValue());
        response.setStatus("UNASSIGNED");

        return response;
    }
}
