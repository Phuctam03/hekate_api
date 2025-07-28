package com.example.demo.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer inventoryId;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private Integer quantity = 0;

    @Column(name = "warehouse_id")
    private Integer warehouseId;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated = LocalDateTime.now();


    public Inventory(Integer inventoryId, Product product, Integer quantity, Integer warehouseId, LocalDateTime lastUpdated) {
        this.inventoryId = inventoryId;
        this.product = product;
        this.quantity = quantity;
        this.warehouseId = warehouseId;
        this.lastUpdated = lastUpdated;
    }

    public Inventory() {
    }

    // Getters and Setters
    public Integer getInventoryId() { return inventoryId; }
    public void setInventoryId(Integer inventoryId) { this.inventoryId = inventoryId; }
    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public Integer getWarehouseId() { return warehouseId; }
    public void setWarehouseId(Integer warehouseId) { this.warehouseId = warehouseId; }
    public LocalDateTime getLastUpdated() { return lastUpdated; }
    public void setLastUpdated(LocalDateTime lastUpdated) { this.lastUpdated = lastUpdated; }
}
