package com.example.demo.service;

import com.example.demo.Exception.CustomerException;
import com.example.demo.Exception.OrderException;
import com.example.demo.Request.OrderItemRequest;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class OrderServiceImlp implements OrderService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PromotionRepository promotionRepository;

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private PromotionService promotionService;

    @Transactional
    @Override
    public Orders createOrder(String zaloUserId, List<OrderItemRequest> items, List<Integer> promotions) {
        // find customer
        Customer customer = customerRepository.findByZaloUserId(zaloUserId).orElseThrow(() -> new CustomerException("Customer not found"));
        // create order
        Orders orders = new Orders();
        orders.setCustomer(customer);
        orders.setOrderStatus("PENDING");
        orders.setCreatedAt(LocalDateTime.now());
        orders.setUpdatedAt(LocalDateTime.now());
        // Calculator subtotal and check Invetory
        BigDecimal totalSubtotal = BigDecimal.ZERO;
        List<OrderDetail> orderDetails = new ArrayList<>();
        for (OrderItemRequest item : items){
            Product product = productRepository.findById(item.getProductId()).orElseThrow(() -> new OrderException("Product not found:"+ item.getProductId()));
            Optional<Inventory> inventory = inventoryService.checkStock(item.getProductId(),item.getWarehouseId(),item.getQuantity());
            if(!inventory.isPresent()){
                throw  new OrderException("insufficient stock for product:"+item.getProductId());
            }

            OrderDetail detail = new OrderDetail();
            detail.setOrder(orders);
            detail.setProduct(product);
            detail.setQuantity(item.getQuantity());
            detail.setUnitPrice(product.getPrice());
            detail.setSubtotal(product.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
            detail.setWarehouse_id(item.getWarehouseId());
            orderDetails.add(detail);
            totalSubtotal = totalSubtotal.add(detail.getSubtotal());

            //substract stock
            inventoryService.updateStock(item.getProductId(),item.getWarehouseId(),item.getQuantity());
        }

        // apply promotion
        List<Promotion> promotionList = promotionRepository.findAllById(promotions);
        BigDecimal discount = promotionService.applyPromotions(promotionList,totalSubtotal);
        orders.setPromotions(promotionList);
        orders.setTotalAmount(totalSubtotal.subtract(discount));
        //save order

        orderRepository.save(orders);
        orderDetailRepository.saveAll(orderDetails);
        return orders;
    }

    @Override
    public Orders cancelOrder(Integer orderId) {
        Optional<Orders> orderOpt = orderRepository.findById(orderId);
        if (orderOpt.isEmpty()) {
            throw new RuntimeException("Order not found: " + orderId);
        }
        Orders order = orderOpt.get();

        if (!order.getOrderStatus().equals("PENDING") && !order.getOrderStatus().equals("CONFIRMED")) {
            throw new OrderException("Cannot cancel order in status: " + order.getOrderStatus());
        }

        // Cập nhật trạng thái đơn hàng
        order.setOrderStatus("CANCELLED");
        order.setUpdatedAt(LocalDateTime.now());

        // Hoàn lại tồn kho
        for (OrderDetail detail : order.getOrderDetails()) {
            inventoryService.restock(detail.getProduct().getProductId(), detail.getWarehouse_id(), detail.getQuantity());
        }

        return orderRepository.save(order);
    }

}
