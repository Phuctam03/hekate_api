package com.example.demo.Request;

import java.time.LocalDateTime;

public class ShippingRequest {
    private Integer orderId;
    private String shippingAddress;
    private String shippingPartner;
    private String trackingNumber;
    private LocalDateTime estimatedDeliveryDate;

    // Getters and Setters
    public Integer getOrderId() { return orderId; }
    public void setOrderId(Integer orderId) { this.orderId = orderId; }
    public String getShippingAddress() { return shippingAddress; }
    public void setShippingAddress(String shippingAddress) { this.shippingAddress = shippingAddress; }
    public String getShippingPartner() { return shippingPartner; }
    public void setShippingPartner(String shippingPartner) { this.shippingPartner = shippingPartner; }
    public String getTrackingNumber() { return trackingNumber; }
    public void setTrackingNumber(String trackingNumber) { this.trackingNumber = trackingNumber; }
    public LocalDateTime getEstimatedDeliveryDate() { return estimatedDeliveryDate; }
    public void setEstimatedDeliveryDate(LocalDateTime estimatedDeliveryDate) { this.estimatedDeliveryDate = estimatedDeliveryDate; }
}
