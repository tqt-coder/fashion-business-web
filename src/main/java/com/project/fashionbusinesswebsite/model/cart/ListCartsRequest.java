package com.project.fashionbusinesswebsite.model.cart;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
//@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListCartsRequest {
    private List<CartResponse> listCarts;

    public void addCart(CartResponse cart) {
        this.listCarts.add(cart);
    }

    public ListCartsRequest() {
        this.listCarts = new ArrayList<>();
    }
}
