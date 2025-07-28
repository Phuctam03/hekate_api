package com.example.demo.Request;

import java.util.List;

public class CreateOrderRequest {
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
