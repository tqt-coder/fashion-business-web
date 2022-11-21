package com.project.fashionbusinesswebsite.model.cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartResponse {
    private int cartId;
    private double money;
    private int quantity;
    private Date date;
    private String productImg;
    private String productTitle;
    private double productPriceAfterDiscount;
    private int status;
}
