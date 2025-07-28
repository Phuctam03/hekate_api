package com.example.demo.service;

import com.example.demo.entity.Promotion;

import java.math.BigDecimal;
import java.util.List;

public interface PromotionService {

    List<Promotion> getCurrentPromotions(BigDecimal totalAmount);

    BigDecimal applyPromotions(List<Promotion> promotions,BigDecimal totalSubtotal);

}
