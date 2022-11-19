package com.project.fashionbusinesswebsite.utils;

import com.project.fashionbusinesswebsite.domain.CustomerEntity;
import com.project.fashionbusinesswebsite.model.user.LoginRequest;
import com.project.fashionbusinesswebsite.repository.AccountRepo;
import com.project.fashionbusinesswebsite.repository.DiscountRepo;
import com.project.fashionbusinesswebsite.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FinderUtil {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private DiscountRepo discountRepo;
    @Autowired
    private AccountRepo accountRepo;

    public List<Double> getAllDiscountsByProductId(int id) {
        List<Double> listPercentDiscounts = discountRepo.getAllDiscountPercentByProductId(id);
        return listPercentDiscounts;
    }

    public CustomerEntity findCustomerByEmail(String email) {
        Optional<CustomerEntity> customerEntity = accountRepo.findCustomerEntityByCustomerEmail(email);
        if (customerEntity.isPresent()) return customerEntity.get();
        return null;
    }

    public CustomerEntity findCustomerByUserName(String userName) {
        Optional<CustomerEntity> customerEntity = accountRepo.findCustomerEntityByUserName(userName);
        if (customerEntity.isPresent()) return customerEntity.get();
        return null;
    }

    public boolean login(LoginRequest request) {
        Optional<CustomerEntity> customerEntity = accountRepo.findCustomerEntityByCustomerEmailAndCustomerPass(request.getCustomerEmail(), request.getCustomerPass());
        if (customerEntity.isPresent()) return true;
        return false;
    }
}
