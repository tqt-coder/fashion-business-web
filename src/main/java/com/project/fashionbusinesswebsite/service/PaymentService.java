package com.project.fashionbusinesswebsite.service;

import com.project.fashionbusinesswebsite.domain.CustomerEntity;
import com.project.fashionbusinesswebsite.domain.PaymentEntity;
import com.project.fashionbusinesswebsite.model.payment.PaymentRequest;
import com.project.fashionbusinesswebsite.model.payment.PaymentResponse;
import com.project.fashionbusinesswebsite.repository.PaymentRepo;
import com.project.fashionbusinesswebsite.utils.FinderUtil;
import com.project.fashionbusinesswebsite.utils.PaymentConstantUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Date;
import java.util.Optional;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepo paymentRepo;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private FinderUtil finderUtil;

    public void createPayment(PaymentRequest request, Principal principal) {
        if (ObjectUtils.isEmpty(principal)) {
            throw new ServiceException("Vui lòng đăng nhập để tiếp tục");
        }
        User user = (User) ((Authentication) principal).getPrincipal();
        CustomerEntity customerEntity = finderUtil.findCustomerByUserName(user.getUsername());
        request.setDate(new Date());
        request.setCustomerId(customerEntity.getCustomerId());
        request.setActive(PaymentConstantUtil.PAYMENT_ACTIVE);

        PaymentEntity paymentEntity = mapper.map(request, PaymentEntity.class);
        paymentRepo.save(paymentEntity);
    }

    public void updatePayment(PaymentRequest request, Principal principal) {
        if (ObjectUtils.isEmpty(principal)) {
            throw new ServiceException("Vui lòng đăng nhập để tiếp tục");
        }
        User user = (User) ((Authentication) principal).getPrincipal();
        CustomerEntity customerEntity = finderUtil.findCustomerByUserName(user.getUsername());
        Optional<PaymentEntity> payment = paymentRepo.findPaymentByCustomerIdAndActive(customerEntity.getCustomerId(), PaymentConstantUtil.PAYMENT_ACTIVE);
        if (payment.isPresent()) {
            PaymentEntity entity = payment.get();
            entity.setActive(request.getActive());
            entity.setPrice(request.getPrice());
            entity.setDate(new Date());
            paymentRepo.save(entity);
        } else {
            throw new ServiceException("Không thể tìm thấy hóa đơn cho khách hàng " + customerEntity.getUserName());
        }
    }

    public PaymentResponse findPayment(Principal principal) {
        if (ObjectUtils.isEmpty(principal)) {
            throw new ServiceException("Vui lòng đăng nhập để tiếp tục");
        }
        User user = (User) ((Authentication) principal).getPrincipal();
        CustomerEntity customerEntity = finderUtil.findCustomerByUserName(user.getUsername());
        Optional<PaymentEntity> payment = paymentRepo.findPaymentByCustomerIdAndActive(customerEntity.getCustomerId(), PaymentConstantUtil.PAYMENT_ACTIVE);
        if (payment.isPresent()) {
            PaymentResponse response = mapper.map(payment.get(), PaymentResponse.class);
            response.setCustomerEmail(customerEntity.getCustomerEmail());
            response.setCustomerName(customerEntity.getCustomerName());
            response.setMoney(payment.get().getPrice());
            return response;
        }
        return null;
    }
}
