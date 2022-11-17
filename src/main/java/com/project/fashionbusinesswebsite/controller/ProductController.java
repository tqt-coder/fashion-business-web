package com.project.fashionbusinesswebsite.controller;

import com.project.fashionbusinesswebsite.model.product.ProductRequest;
import com.project.fashionbusinesswebsite.model.product.SearchProductRequest;
import com.project.fashionbusinesswebsite.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/get-all-products")
    public ResponseEntity<?> getAllProducts(@RequestBody ProductRequest request) {
        return ResponseEntity.ok(productService.getAllProduct(request));
    }

    @GetMapping("/get-product-by-id/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") int id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping("/get-product-by-product-name")
    public ResponseEntity<?> findProductByProductName(@RequestBody SearchProductRequest request) {
        return ResponseEntity.ok(productService.findProductsByProductName(request));
    }
}
