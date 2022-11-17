package com.project.fashionbusinesswebsite.repository;

import com.project.fashionbusinesswebsite.domain.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AccountRepo extends JpaRepository<CustomerEntity, Integer> {
}
