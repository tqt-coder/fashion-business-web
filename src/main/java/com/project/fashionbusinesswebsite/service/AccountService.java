package com.project.fashionbusinesswebsite.service;

import com.project.fashionbusinesswebsite.domain.CustomerEntity;
import com.project.fashionbusinesswebsite.model.user.AccountResponse;
import com.project.fashionbusinesswebsite.model.user.LoginRequest;
import com.project.fashionbusinesswebsite.model.user.RegisterRequest;
import com.project.fashionbusinesswebsite.repository.AccountRepo;
import com.project.fashionbusinesswebsite.repository.RoleRepo;
import com.project.fashionbusinesswebsite.repository.RoleUserRepo;
import com.project.fashionbusinesswebsite.utils.FinderUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AccountService {
    @Autowired
    private AccountRepo accountRepo;
    //    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private FinderUtil finderUtil;
    @Autowired
    private RoleUserRepo roleUserRepo;
    @Autowired
    private RoleRepo roleRepo;

    public CustomerEntity register(RegisterRequest request) {
        checkAccount(request.getCustomerEmail(), request.getUserName());
        String encodedPassword = new BCryptPasswordEncoder().encode(request.getCustomerPass().trim());
        request.setCustomerPass(encodedPassword);
        CustomerEntity entity = mapper.map(request, CustomerEntity.class);
//        accountRepo.save(entity);
        return accountRepo.save(entity);
    }

    public CustomerEntity updateCustomer(RegisterRequest request) {
        String encodedPassword = new BCryptPasswordEncoder().encode(request.getCustomerPass().trim());
        request.setCustomerPass(encodedPassword);
        CustomerEntity entity = mapper.map(request, CustomerEntity.class);
        return accountRepo.save(entity);
    }

    public boolean login(LoginRequest request) {
        CustomerEntity customerEntity = finderUtil.findCustomerByEmail(request.getCustomerEmail());
        if (ObjectUtils.isEmpty(customerEntity))
            throw new ServiceException("Email hoặc password không đúng. Vui lòng thử lại");
        return true;
    }

    public Set<String> getAllRoleNamesByUserId(String userId) {
        List<Integer> listRoleIds = roleUserRepo.findAllRoleIdByUserId(userId);
        Set<String> roleNames = new HashSet<>();
        if (CollectionUtils.isNotEmpty(listRoleIds)) {
            listRoleIds.stream().map(x -> roleRepo.findById(x)).filter(x -> Boolean.TRUE.equals(x.isPresent())).forEach(y -> roleNames.add(y.get().getRoleName()));
        }
        return roleNames;
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

    public AccountResponse getInformaionOfCustomer(Principal principal) {
        if (ObjectUtils.isEmpty(principal)) {
            throw new ServiceException("Vui lòng đăng nhập để tiếp tục");
        }
        User user = (User) ((Authentication) principal).getPrincipal();
        CustomerEntity customerEntity = finderUtil.findCustomerByUserName(user.getUsername());
        AccountResponse response = mapper.map(customerEntity, AccountResponse.class);
        return response;
    }
}
