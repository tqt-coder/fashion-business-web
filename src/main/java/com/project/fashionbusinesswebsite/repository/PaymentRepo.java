package com.project.fashionbusinesswebsite.repository;

import com.project.fashionbusinesswebsite.domain.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PaymentRepo extends JpaRepository<PaymentEntity, Integer> {
    @Query("select u from PaymentEntity u where u.customerId = :customerId and u.active = :active")
    Optional<PaymentEntity> findPaymentByCustomerIdAndActive(String customerId, int active);
}
