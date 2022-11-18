package com.project.fashionbusinesswebsite.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequest {
    @Email(message = "Vui lòng nhập email")
    @NotBlank(message = "Email của khách hàng không được để trống")
    private String customerEmail;
    @NotBlank(message = "Mật khẩu của khàng không được để trống")
    @Size(min = 8, message = "Mật khẩu phải dài hơn 8 kí tự")
    private String customerPass;
}
