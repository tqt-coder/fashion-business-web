package com.project.fashionbusinesswebsite.repository;

import com.project.fashionbusinesswebsite.domain.DiscountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface DiscountRepo extends JpaRepository<DiscountEntity, Integer> {
    @Query("select de.discountPercent from DiscountEntity de where de.productId = :productId")
    List<Double> getAllDiscountPercentByProductId(int productId);
}
