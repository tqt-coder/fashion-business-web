package com.project.fashionbusinesswebsite.controller;

import com.project.fashionbusinesswebsite.service.StripeService;
import com.stripe.model.Coupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentRestController {
    @Autowired
    private StripeService stripeService;

    @PostMapping("/cancel-subscription")
    public ResponseEntity<?> cancelSubscription(String subscriptionId) {
        boolean status = stripeService.cancelSubscription(subscriptionId);
        if (!status) {
            return new ResponseEntity<String>("Failed to cancel the subscription. Please, try later.", HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<String>("Subscription cancelled successfully.", HttpStatus.OK);
    }

    @PostMapping("/coupon-validator")
    public ResponseEntity<?> couponValidator(String code) {
        Coupon coupon = stripeService.retrieveCoupon(code);
        if (coupon != null && coupon.getValid()) {
            String details = (coupon.getPercentOff() == null ? "$" + (coupon.getAmountOff() / 100) : coupon.getPercentOff() + "%") +
                    " OFF " + coupon.getDuration();
            return new ResponseEntity<String>(details, HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("This coupon code is not available. This may be because it has expired or has already been applied to your account.", HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PostMapping("/create-charge")
    public ResponseEntity<?> createCharge(String email, String token) {
        //validate data
        if (token == null) {
            return new ResponseEntity<String>("Stripe payment token is missing. Please, try again later.", HttpStatus.EXPECTATION_FAILED);
        }

        //create charge
        String chargeId = stripeService.createCharge(email, token, 999); //$9.99 USD
        if (chargeId == null) {
           return new ResponseEntity<String>("An error occurred while trying to create a charge.", HttpStatus.BAD_REQUEST);
        }

        // You may want to store the charge id along with order information
        return new ResponseEntity<String>("Success! Your charge id is " + chargeId, HttpStatus.OK);
    }
}
