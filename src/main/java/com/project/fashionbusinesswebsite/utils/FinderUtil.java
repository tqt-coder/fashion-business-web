package com.project.fashionbusinesswebsite.utils;

import com.project.fashionbusinesswebsite.repository.DiscountRepo;
import com.project.fashionbusinesswebsite.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinderUtil {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private DiscountRepo discountRepo;

    public List<Double> getAllDiscountsByProductId(int id) {
        List<Double> listPercentDiscounts = discountRepo.getAllDiscountPercentByProductId(id);
        return listPercentDiscounts;
    }
}
