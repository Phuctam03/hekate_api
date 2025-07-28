package com.example.demo.repository;

import com.example.demo.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface PromotionRepository  extends JpaRepository<Promotion,Integer> {
   @Query("SELECT p FROM Promotion p WHERE p.isActive = true AND :now BETWEEN p.startDate AND p.endDate AND (p.minOrderValue IS NULL OR p.minOrderValue <= :totalAmount)")
    List<Promotion> findActivePromotions(LocalDateTime now, BigDecimal totalAmount);


}
