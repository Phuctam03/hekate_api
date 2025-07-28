package com.example.demo.repository;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository  extends JpaRepository<Inventory,Integer> {
    Optional<Inventory> findByProductProductIdAndWarehouseId(Integer productId, Integer warehouseId);
}
