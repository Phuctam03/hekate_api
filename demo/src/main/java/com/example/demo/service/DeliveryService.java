package com.example.demo.service;

import com.example.demo.entity.Delivery;

import java.time.LocalDateTime;

public interface DeliveryService {

    Delivery sendShippingInfo(Integer orderId, String shippingAddress, String shippingPartner, String trackingnumber, LocalDateTime estimatedDeliveryDate);

}
