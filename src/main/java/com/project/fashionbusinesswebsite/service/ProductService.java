package com.project.fashionbusinesswebsite.service;

import com.project.fashionbusinesswebsite.domain.ProductEntity;
import com.project.fashionbusinesswebsite.model.page.PageModel;
import com.project.fashionbusinesswebsite.model.product.ProductRequest;
import com.project.fashionbusinesswebsite.model.product.ProductResponse;
import com.project.fashionbusinesswebsite.repository.ProductRepo;
import com.project.fashionbusinesswebsite.utils.FinderUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private FinderUtil finderUtil;

    public List<ProductResponse> getAllProduct(ProductRequest request) {
        PageModel page = mapper.map(request,PageModel.class);
        Sort sortable = null;
        if ("ASC".equals(page.getSort())) {
            sortable = Sort.by(request.getKey()).ascending();
        }
        if ("DESC".equals(page.getSort())) {
            sortable = Sort.by(request.getKey()).descending();
        }
        List<ProductEntity> listProudcts = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(sortable)) {
            Pageable pageable = PageRequest.of(page.getPage(), page.getSize(), sortable);
            listProudcts = productRepo.findAll(pageable).toList();

        } else {
            listProudcts = productRepo.findAll();
        }

        List<ProductResponse> responses = new ArrayList<>();

        if (CollectionUtils.isNotEmpty(listProudcts)) {
            listProudcts.forEach(x -> {
                ProductResponse product = mapper.map(x, ProductResponse.class);
                List<Double> listDiscounts = finderUtil.getAllDiscountsByProductId(product.getProductsId());
                product.setProductPriceAfterDiscount(calculatePriceAfterDiscount(product.getProductPrice(),listDiscounts));
                responses.add(product);
            });
        }
        return responses;
    }

    private double calculatePriceAfterDiscount(double productPrice, List<Double> listDiscounts) {
        if (CollectionUtils.isEmpty(listDiscounts)) return productPrice;
        double maxDiscount = listDiscounts.stream().max(Double::compare).orElseGet(() -> 1.0);
        return productPrice - (productPrice * maxDiscount / 100);
    }

}
