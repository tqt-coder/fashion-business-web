package com.project.fashionbusinesswebsite.service;

import com.project.fashionbusinesswebsite.domain.CustomerEntity;
import com.project.fashionbusinesswebsite.model.user.RegisterRequest;
import com.project.fashionbusinesswebsite.repository.AccountRepo;
import com.project.fashionbusinesswebsite.utils.FinderUtil;
import org.apache.commons.lang3.ObjectUtils;
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
    @Autowired
    private FinderUtil finderUtil;

    public boolean register(RegisterRequest request) {
        checkAccount(request.getCustomerEmail(), request.getUserName());
        String encodedPassword = bCryptPasswordEncoder.encode(request.getCustomerPass());
        request.setCustomerPass(encodedPassword);
        CustomerEntity entity = mapper.map(request, CustomerEntity.class);
        accountRepo.save(entity);
        return true;

    }
    public boolean login(RegisterRequest request) {
        checkAccount(request.getCustomerEmail(), request.getUserName());
        String encodedPassword = bCryptPasswordEncoder.encode(request.getCustomerPass());
        request.setCustomerPass(encodedPassword);
        CustomerEntity entity = mapper.map(request, CustomerEntity.class);
        accountRepo.save(entity);
        return true;

    }

    private void checkAccount(String customerEmail, String userName) {
        CustomerEntity customerByEmail = finderUtil.findCustomerByEmail(customerEmail);
        if (ObjectUtils.isNotEmpty(customerByEmail)) {
            throw new ServiceException("Email " + customerEmail + " đã tồn tại. Vui lòng chọn lại");
        }
        CustomerEntity customerEntityByUserName = finderUtil.findCustomerByUserName(userName);
        if (ObjectUtils.isNotEmpty(customerEntityByUserName)) {
            throw new ServiceException("User name " + userName + " đã tồn tại. Vui lòng chọn lại");
        }
    }
}
