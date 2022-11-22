package com.project.fashionbusinesswebsite.controller;

import com.project.fashionbusinesswebsite.domain.CustomerEntity;
import com.project.fashionbusinesswebsite.model.user.LoginRequest;
import com.project.fashionbusinesswebsite.model.user.RegisterRequest;
import com.project.fashionbusinesswebsite.service.AccountService;
import com.project.fashionbusinesswebsite.service.ServiceException;
import com.stripe.Stripe;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Value("${stripe.apikey}")
    String stripeKey;

    @PostMapping("/create")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterRequest request) {
        Stripe.apiKey = stripeKey;
        String customerId = "cus_MqfRoc3UDdPcYY";
        Map<String, Object> params = new HashMap<>();
        try {
            params.put("name", request.getUserName());
            params.put("email", request.getCustomerEmail());
            Customer customer = Customer.create(params);
            request.setCustomerId(customer.getId());

        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
        return ResponseEntity.ok(request);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest request) {
        return ResponseEntity.ok(accountService.login(request));
    }

}
