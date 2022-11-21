package com.project.fashionbusinesswebsite.model.cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartRequest {
    private int productsId;
    private int quantity;
    private double money;
}
