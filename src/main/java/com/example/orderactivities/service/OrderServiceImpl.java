package com.example.orderactivities.service;

import com.example.orderactivities.dto.DistanceMatrixResponse;
import com.example.orderactivities.dto.OrderRequest;
import com.example.orderactivities.dto.OrderResponse;
import com.example.orderactivities.entity.OrderEntity;
import com.example.orderactivities.exception.NoResultStatusException;
import com.example.orderactivities.repository.OrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class OrderServiceImpl implements  OrderService{

    static final String UNASSIGNED_STATUS = "UNASSIGNED";
    static final String ZERO_RESULTS_STATUS = "ZERO_RESULTS";
    static final String SUCCESS_STATUS = "SUCCESS";
    static final String FAIL_STATUS = "FAIL";

    @Autowired
    DistanceMatrixService distanceService;

    @Autowired
    OrderRepository orderRepository;

    public OrderResponse processOrderRequest(OrderRequest request) throws NoResultStatusException {

        DistanceMatrixResponse result = distanceService.getDistance(request);
        if(result.getRows().get(0).getElements().get(0).getStatus().equals(ZERO_RESULTS_STATUS)) {
            throw new NoResultStatusException("Distance matrix returned: " +ZERO_RESULTS_STATUS);
        } else {
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setDistance(result.getRows().get(0).getElements().get(0).getDistance().getValue());
            orderEntity.setStatus(UNASSIGNED_STATUS);
            orderEntity = orderRepository.saveAndFlush(orderEntity);

            OrderResponse response = new OrderResponse();
            response.setId(orderEntity.getId().toString());
            response.setDistance(orderEntity.getDistance());
            response.setStatus(orderEntity.getStatus());
            return response;
        }
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public String processTakeOrderRequest(Integer id, String status) {
        OrderResponse response = new OrderResponse();
        OrderEntity updateEntity  = new OrderEntity();
        Optional<OrderEntity> order = orderRepository.findById(id);
        if(order.isPresent()) {
            updateEntity  = order.get();
            updateEntity.setStatus(status);
            updateEntity = orderRepository.saveAndFlush(updateEntity);
        }
        if(Objects.nonNull(updateEntity) && updateEntity.getStatus().equals(status)) {
            return SUCCESS_STATUS;
        } else {
            return FAIL_STATUS;
        }
    }

    @Override
    public List<OrderResponse> processOrderList(Integer page, Integer limit) {
        List<OrderResponse> responseList = new ArrayList<>();
        OrderResponse response = new OrderResponse();
        Pageable pageable = PageRequest.of(page-1, limit);
        Page<OrderEntity> listOrders =  orderRepository.findAll(pageable);
        listOrders.forEach(record -> {
            BeanUtils.copyProperties(record, response);
            responseList.add(response);
                });
        return responseList;
    }

}
