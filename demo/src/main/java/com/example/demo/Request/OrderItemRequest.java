package com.example.demo.Request;

public class OrderItemRequest {
    private Integer productId;
    private Integer quantity;
    private Integer warehouseId;


    public OrderItemRequest(Integer productId, Integer quantity, Integer warehouseId) {
        this.productId = productId;
        this.quantity = quantity;
        this.warehouseId = warehouseId;
    }

    // Getters and Setters
    public Integer getProductId() { return productId; }
    public void setProductId(Integer productId) { this.productId = productId; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public Integer getWarehouseId() { return warehouseId; }
    public void setWarehouseId(Integer warehouseId) { this.warehouseId = warehouseId; }
}
