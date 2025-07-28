package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "ZaloUsers")
public class ZaloUser {
    @Id
    @Column(name = "zalo_user_id", length = 50)
    private String zaloUserId;

    @Column(length = 100)
    private String name;

    @Column(length = 20)
    private String phone;

    @Column(name = "last_interaction")
    private LocalDateTime lastInteraction = LocalDateTime.now();

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public ZaloUser(String zaloUserId, String name, String phone, LocalDateTime lastInteraction, LocalDateTime createdAt) {
        this.zaloUserId = zaloUserId;
        this.name = name;
        this.phone = phone;
        this.lastInteraction = lastInteraction;
        this.createdAt = createdAt;
    }

    public ZaloUser() {
    }

    // Getters and Setters
    public String getZaloUserId() { return zaloUserId; }
    public void setZaloUserId(String zaloUserId) { this.zaloUserId = zaloUserId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public LocalDateTime getLastInteraction() { return lastInteraction; }
    public void setLastInteraction(LocalDateTime lastInteraction) { this.lastInteraction = lastInteraction; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
