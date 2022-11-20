package com.project.fashionbusinesswebsite.controller;

import com.project.fashionbusinesswebsite.domain.ProductCategoryEntity;
import com.project.fashionbusinesswebsite.model.product.*;
import com.project.fashionbusinesswebsite.service.ProductService;
import com.project.fashionbusinesswebsite.service.ServiceException;
import com.project.fashionbusinesswebsite.utils.FinderUtil;
import com.project.fashionbusinesswebsite.utils.ProductConstantUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    private ProductService productService;
    @Autowired
    private FinderUtil finderUtil;

    @GetMapping("/")
    public String homePage(ProductRequest request, Model model) {
        // category with shirt
        int categoryIdShirt = ProductConstantUtil.SHIRT;
        ProductCategoryRequest productCategoryRequest = new ProductCategoryRequest();
        productCategoryRequest.setCategoryId(categoryIdShirt);
        model.addAttribute("listShirts", productService.findAllProductsByProductCategory(productCategoryRequest));
        // category with hat
        int categoryIdHat = ProductConstantUtil.HAT;
        productCategoryRequest.setCategoryId(categoryIdHat);
        model.addAttribute("listHats", productService.findAllProductsByProductCategory(productCategoryRequest));
        // category with hat
        int categoryIdJacket = ProductConstantUtil.JACKET;
        productCategoryRequest.setCategoryId(categoryIdJacket);
        model.addAttribute("listJackets", productService.findAllProductsByProductCategory(productCategoryRequest));

        model.addAttribute("listProducts", productService.getAllProduct(request));
        return "index";
    }

    @GetMapping("/search")
    public String searchPage(@RequestParam(name = "keySearch") String keySearch,
                             @RequestParam(name = "page") int currentPage, Model model) {
        SearchProductRequest request = new SearchProductRequest();
        request.setKeySearch(keySearch);
        request.setPage(currentPage);
        request.setSize(24);
        int totalPag = productService.getSizePageByProductTitle(keySearch);
        int numberPage = Integer.valueOf(totalPag / 24) + 1;
        List<ProductResponse> result = productService.findProductsByProductName(request);
        model.addAttribute("sizeResult", result.size());
        model.addAttribute("numberPage", numberPage);
        model.addAttribute("totalResult", totalPag);
        model.addAttribute("keySearch", keySearch);
        model.addAttribute("currentPage", request.getPage());
        model.addAttribute("listProducts", productService.findProductsByProductName(request));
        return "category";
    }

    @GetMapping("/detail")
    public String detailPage(@RequestParam(name = "productId") int productId, Model model) {
        ProductViewResponse product = productService.getProductById(productId);
        ProductCategoryRequest productCategoryRequest = new ProductCategoryRequest();
        productCategoryRequest.setCategoryId(product.getProductCategoryId());
        model.addAttribute("listProducts", productService.findAllProductsByProductCategory(productCategoryRequest));
        ProductCategoryEntity productCategoryEntity = finderUtil.findProductCategoryById(product.getProductCategoryId());
        if(ObjectUtils.isEmpty(productCategoryEntity)){
            throw new ServiceException("Dang mục sản phẩm không tồn tại");
        }
        product.setProductCategoryName(productCategoryEntity.getPCategoryTitle());
        model.addAttribute("product",product);
        return "productDetail";
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
