package com.project.fashionbusinesswebsite.service;

import com.project.fashionbusinesswebsite.domain.CartEntity;
import com.project.fashionbusinesswebsite.domain.CustomerEntity;
import com.project.fashionbusinesswebsite.model.cart.CartRequest;
import com.project.fashionbusinesswebsite.model.product.ProductViewResponse;
import com.project.fashionbusinesswebsite.repository.CartRepo;
import com.project.fashionbusinesswebsite.utils.CartConstantUtil;
import com.project.fashionbusinesswebsite.utils.FinderUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Date;


@Service
public class CartService {
    @Autowired
    private CartRepo cartRepo;
    @Autowired
    private ProductService productService;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private FinderUtil finderUtil;


    public boolean createCart(CartRequest request, Principal principal) {
        ProductViewResponse product = productService.getProductById(request.getProductsId());
        if (ObjectUtils.isEmpty(product)) {
            throw new ServiceException("Không thể tìm thấy sản phẩm với id = " + request.getProductsId());
        }
        User user = (User) ((Authentication) principal).getPrincipal();
        if (ObjectUtils.isEmpty(user)) {
            throw new ServiceException("Vui lòng đăng nhập để tiếp tục");
        }
        CustomerEntity customerEntity = finderUtil.findCustomerByUserName(user.getUsername());
        CartEntity cartEntity = mapper.map(request, CartEntity.class);
        cartEntity.setStatus(CartConstantUtil.CART_CREAT);
        cartEntity.setDate(new Date());
        cartEntity.setCustomerId(customerEntity.getCustomerId());

        cartRepo.save(cartEntity);
        return true;
    }
}
