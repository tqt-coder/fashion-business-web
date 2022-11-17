package com.project.fashionbusinesswebsite.controller;

import com.project.fashionbusinesswebsite.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/get-all-products")
    public ResponseEntity<?> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProduct());
    }
}
