package com.project.fashionbusinesswebsite.model.payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentResponse {
    private double money;
    private int active;
    private Date date;
    private String customerId;
    private String customerEmail;
    private String customerName;
}
