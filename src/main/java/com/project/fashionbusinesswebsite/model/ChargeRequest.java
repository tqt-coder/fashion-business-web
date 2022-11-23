package com.project.fashionbusinesswebsite.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChargeRequest {

    public enum Currency {
        EUR, USD;
    }
    private String description;
    private double amount;
    private Currency currency;
    private String stripeEmail;
    private String stripeToken;
}
