package com.example.demo.service;

import com.example.demo.Exception.OrderException;
import com.example.demo.entity.Delivery;
import com.example.demo.entity.Orders;
import com.example.demo.repository.DeliveryRepository;
import com.example.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class DeliveryServiceImlp  implements  DeliveryService{

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private DeliveryRepository deliveryRepository;
    @Override
    public Delivery sendShippingInfo(Integer orderId, String shippingAddress, String shippingPartner, String trackingnumber, LocalDateTime estimatedDeliveryDate) {
        Orders orders = orderRepository.findById(orderId).orElseThrow(() -> new OrderException("Order not found"));

        Delivery delivery = new Delivery();
        delivery.setOrder(orders);
        delivery.setShippingAddress(shippingAddress);
        delivery.setShippingPartner(shippingPartner);
        delivery.setShippingStatus("PREPARING");
        delivery.setEstimatedDeliveryDate(estimatedDeliveryDate);
        delivery.setCreatedAt(LocalDateTime.now());
        delivery.setUpdatedAt(LocalDateTime.now());
        return deliveryRepository.save(delivery);
    }
}
