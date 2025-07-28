package com.example.demo.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Delivery")
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer deliveryId;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Orders order;

    @Column(name = "shipping_address", nullable = false, columnDefinition = "TEXT")
    private String shippingAddress;



    @Column(name = "tracking_number", length = 50)
    private String trackingNumber;

    @Column(name = "shipping_partner", length = 100)
    private String shippingPartner;

    @Column(name = "estimated_delivery_date")
    private LocalDateTime estimatedDeliveryDate;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    @Column(name = "shipping_status")
    private String shippingStatus;


    public Delivery(Integer deliveryId, Orders order, String shippingAddress, String trackingNumber, String shippingPartner, LocalDateTime estimatedDeliveryDate, LocalDateTime createdAt, LocalDateTime updatedAt, String shippingStatus) {
        this.deliveryId = deliveryId;
        this.order = order;
        this.shippingAddress = shippingAddress;
        this.trackingNumber = trackingNumber;
        this.shippingPartner = shippingPartner;
        this.estimatedDeliveryDate = estimatedDeliveryDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.shippingStatus = shippingStatus;
    }

    public Delivery() {
    }

    // Getters and Setters
    public Integer getDeliveryId() { return deliveryId; }
    public void setDeliveryId(Integer deliveryId) { this.deliveryId = deliveryId; }
    public Orders getOrder() { return order; }
    public void setOrder(Orders order) { this.order = order; }
    public String getShippingAddress() { return shippingAddress; }
    public void setShippingAddress(String shippingAddress) { this.shippingAddress = shippingAddress; }
    public String getShippingStatus() { return shippingStatus; }
    public void setShippingStatus(String shippingStatus) { this.shippingStatus = shippingStatus; }
    public String getTrackingNumber() { return trackingNumber; }
    public void setTrackingNumber(String trackingNumber) { this.trackingNumber = trackingNumber; }
    public String getShippingPartner() { return shippingPartner; }
    public void setShippingPartner(String shippingPartner) { this.shippingPartner = shippingPartner; }
    public LocalDateTime getEstimatedDeliveryDate() { return estimatedDeliveryDate; }
    public void setEstimatedDeliveryDate(LocalDateTime estimatedDeliveryDate) { this.estimatedDeliveryDate = estimatedDeliveryDate; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
