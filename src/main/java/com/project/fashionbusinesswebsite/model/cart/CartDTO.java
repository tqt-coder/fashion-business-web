package com.project.fashionbusinesswebsite.model.cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartDTO {
    private int cartId;
    private double money;
    private int quantity;
    private int status;
}
