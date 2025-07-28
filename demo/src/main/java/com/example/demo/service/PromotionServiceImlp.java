package com.example.demo.service;

import com.example.demo.entity.Promotion;
import com.example.demo.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class PromotionServiceImlp implements  PromotionService{


    @Autowired
    private PromotionRepository promotionRepository;
    @Override
    public List<Promotion> getCurrentPromotions(BigDecimal totalAmount) {
        return  promotionRepository.findActivePromotions(LocalDateTime.now(),totalAmount);
    }

    @Override
    public BigDecimal applyPromotions(List<Promotion> promotions, BigDecimal totalSubtotal) {
        BigDecimal discount =BigDecimal.ZERO;
        for (Promotion promotion : promotions) {
            if (promotion.getIsActive() && LocalDateTime.now().isAfter(promotion.getStartDate()) &&
                    LocalDateTime.now().isBefore(promotion.getEndDate()) &&
                    (promotion.getMinOrderValue() == null || totalSubtotal.compareTo(promotion.getMinOrderValue()) >= 0)) {
                if (promotion.getDiscountType().equals("PERCENTAGE")) {
                    discount = discount.add(totalSubtotal.multiply(promotion.getDiscountValue()).divide(BigDecimal.valueOf(100)));
                } else {
                    discount = discount.add(promotion.getDiscountValue());
                }
            }
        }
        return discount ;
    }
}
