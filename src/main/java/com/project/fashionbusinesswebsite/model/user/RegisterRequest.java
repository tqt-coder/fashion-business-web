package com.project.fashionbusinesswebsite.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequest {
    @NotBlank(message = "Tên của khách hàng không được để trống")
    private String userName;
    @NotBlank(message = "Họ tên của khách hàng không được để trống")
    private String customerName;
    @Email(message = "Vui lòng nhập email")
    @NotBlank(message = "Email của khách hàng không được để trống")
    private String customerEmail;
    @NotBlank(message = "Mật khẩu của khàng không được để trống")
    @Size(min = 8, message = "Mật khẩu phải dài hơn 8 kí tự")
    private String customerPass;
    @NotBlank(message = "Địa chỉ không được để trống")
    private String customerAddress;
    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(regexp = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$", message = "Vui lòng nhập đúng số điện thoại")
    private String customerContact;
    private String customerImage;
}
