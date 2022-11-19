package com.project.fashionbusinesswebsite.controller;

import com.project.fashionbusinesswebsite.model.product.ProductCategoryRequest;
import com.project.fashionbusinesswebsite.model.product.ProductRequest;
import com.project.fashionbusinesswebsite.service.ProductService;
import com.project.fashionbusinesswebsite.utils.ProductConstantUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class MainController {
    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String homePage(ProductRequest request, Model model) {
        // category with shirt
        int categoryId = ProductConstantUtil.SHIRT;
        ProductCategoryRequest productCategoryRequest = new ProductCategoryRequest();
        productCategoryRequest.setCategoryId(categoryId);
        model.addAttribute("listShirts", productService.findAllProductsByProductCategory(productCategoryRequest));
        model.addAttribute("listProducts",productService.getAllProduct(request));
        return "index";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @GetMapping("/userAccountInfo")
    @ResponseBody
    public String successPage() {
        return "<h2>Success </h2>";
    }

    @GetMapping("/403")
    @ResponseBody
    public String errorPage() {
        return "<h1>Bug</h1>";
    }

    @GetMapping("/test")
    @ResponseBody
    public String test(Principal principal) {
        User user = (User) ((Authentication) principal).getPrincipal();

        return "hello";
    }
}
