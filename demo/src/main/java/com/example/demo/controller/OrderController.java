package com.example.demo.controller;


import com.example.demo.Request.CreateOrderRequest;
import com.example.demo.Request.OrderItemRequest;
import com.example.demo.Request.ShippingRequest;
import com.example.demo.entity.Delivery;
import com.example.demo.entity.Inventory;
import com.example.demo.entity.Orders;
import com.example.demo.entity.Promotion;
import com.example.demo.service.DeliveryService;
import com.example.demo.service.InventoryService;
import com.example.demo.service.OrderService;
import com.example.demo.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private PromotionService promotionService;
    @Autowired
    private DeliveryService deliveryService;

    @GetMapping("/stock/check")
    public ResponseEntity<?> checkStock(@RequestParam Integer productId, @RequestParam Integer warehouseId, @RequestParam Integer quantity) {
        Optional<Inventory> inventory = inventoryService.checkStock(productId, warehouseId, quantity);
        return inventory.isPresent() ? ResponseEntity.ok(inventory.get()) : ResponseEntity.badRequest().body("Insufficient stock");
    }

    @PostMapping("/orders/create")
    public ResponseEntity<?> createOrder(@RequestBody CreateOrderRequest request) {
        try {
            Orders order = orderService.createOrder(request.getZaloUserId(), request.getItems(), request.getPromotionIds());
            return ResponseEntity.ok(order);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/promotions/current")
    public ResponseEntity<List<Promotion>> getCurrentPromotions(@RequestParam BigDecimal totalAmount) {
        return ResponseEntity.ok(promotionService.getCurrentPromotions(totalAmount));
    }

    @PostMapping("/shipping/send")
    public ResponseEntity<Delivery> sendShippingInfo(@RequestBody ShippingRequest request) {
        Delivery delivery = deliveryService.sendShippingInfo(
                request.getOrderId(),
                request.getShippingAddress(),
                request.getShippingPartner(),
                request.getTrackingNumber(),
                request.getEstimatedDeliveryDate()
        );
        return ResponseEntity.ok(delivery);
    }


    @PostMapping("/orders/{id}/cancel")
    public ResponseEntity<Orders> cancelOrder(@PathVariable("id") Integer orderId) {
        Orders cancelledOrder = orderService.cancelOrder(orderId);
        return ResponseEntity.ok(cancelledOrder);
    }
    public static class CreateOrderRequest {
        private String zaloUserId;
        private List<OrderItemRequest> items;
        private List<Integer> promotionIds;

        // Getters and Setters
        public String getZaloUserId() { return zaloUserId; }
        public void setZaloUserId(String zaloUserId) { this.zaloUserId = zaloUserId; }
        public List<OrderItemRequest> getItems() { return items; }
        public void setItems(List<OrderItemRequest> items) { this.items = items; }
        public List<Integer> getPromotionIds() { return promotionIds; }
        public void setPromotionIds(List<Integer> promotionIds) { this.promotionIds = promotionIds; }
    }


}
