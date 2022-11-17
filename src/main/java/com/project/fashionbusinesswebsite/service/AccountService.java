package com.project.fashionbusinesswebsite.service;

import com.project.fashionbusinesswebsite.domain.CustomerEntity;
import com.project.fashionbusinesswebsite.model.user.RegisterRequest;
import com.project.fashionbusinesswebsite.repository.AccountRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private ModelMapper mapper;

    public boolean register(RegisterRequest request) {
        String encodedPassword = bCryptPasswordEncoder.encode(request.getCustomerPass());
        request.setCustomerPass(encodedPassword);
        CustomerEntity entity = mapper.map(request, CustomerEntity.class);
        accountRepo.save(entity);
        return true;

    }
}
