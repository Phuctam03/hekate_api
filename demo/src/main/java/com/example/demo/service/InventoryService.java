package com.example.demo.service;

import com.example.demo.entity.Inventory;

import java.util.Optional;

public interface InventoryService {

    Optional<Inventory> checkStock(Integer productId, Integer warehouseId, Integer quantity);
    void updateStock(Integer productId,Integer warehouseId,Integer quantity);
    void restock(Integer productId,Integer warehouseId,Integer quantity);


}
