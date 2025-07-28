package com.example.demo.service;

import com.example.demo.Exception.InventoryException;
import com.example.demo.entity.Inventory;
import com.example.demo.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class InventoryServiceImlp implements InventoryService {


    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public Optional<Inventory> checkStock(Integer productId, Integer warehouseId, Integer quantity) {
        Optional<Inventory> inventory = inventoryRepository.findByProductProductIdAndWarehouseId(productId, warehouseId);
        if (inventory.isPresent() && inventory.get().getQuantity() >= quantity) {
            return inventory;
        }
        return Optional.empty();
    }

    @Override
    public void updateStock(Integer productId, Integer warehouseId, Integer quantity) {
        Optional<Inventory> inventory = inventoryRepository.findByProductProductIdAndWarehouseId(productId, warehouseId);
        if (inventory.isPresent()) {
            Inventory inv = inventory.get();
            inv.setQuantity(inv.getQuantity() - quantity);
            inventoryRepository.save(inv);
        } else {
            throw new InventoryException("Insufficient stock for product : " + productId);
        }
    }

    @Override
    public void restock(Integer productId, Integer warehouseId, Integer quantity) {
        Optional<Inventory> inventory = inventoryRepository.findByProductProductIdAndWarehouseId(productId, warehouseId);
        if (inventory.isPresent()) {
            Inventory inv = inventory.get();
            inv.setQuantity(inv.getQuantity() + quantity);
            inv.setLastUpdated(LocalDateTime.now());
            inventoryRepository.save(inv);
        } else {
            throw new RuntimeException("Inventory not found for product: " + productId + ", warehouse: " + warehouseId);
        }
    }
}

