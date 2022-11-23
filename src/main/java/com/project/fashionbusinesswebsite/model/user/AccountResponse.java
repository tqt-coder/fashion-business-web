package com.project.fashionbusinesswebsite.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountResponse {
    private String userName;
    private String customerName;
    private String customerEmail;
    private String customerPass;
    private String customerAddress;
    private String customerContact;
    private String customerImage;
    private String customerId;
}
