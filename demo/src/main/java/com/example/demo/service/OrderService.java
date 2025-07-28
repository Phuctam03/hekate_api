package com.example.demo.service;

import com.example.demo.Request.OrderItemRequest;
import com.example.demo.entity.Orders;

import java.util.List;

public interface OrderService {

    Orders createOrder(String zaloUserId, List<OrderItemRequest> items,List<Integer> promotions);

}
