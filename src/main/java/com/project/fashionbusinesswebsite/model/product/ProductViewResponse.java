package com.project.fashionbusinesswebsite.model.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductViewResponse {
    private int productsId;
    private String productTitle;
    private String productImg;
    private double productPrice;
    private double productPriceAfterDiscount;
    private int productQuantity;
    private String productDescription;
}
