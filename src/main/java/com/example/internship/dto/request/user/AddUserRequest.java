package com.example.internship.dto.request.user;

import com.example.internship.utils.validation.add.UniqueEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddUserRequest {
    @NotNull(message = "Username không được bỏ trống")
    private String username;
    @NotNull(message = "Mật khẩu không được bỏ trống")
    private String password;
    @NotNull(message = "Tên đầy đủ không được bỏ trống")
    private String fullName;
    @NotNull(message = "Email không được bỏ trống")
    @Email(message = "Email không đúng định dạng")
    @UniqueEmail(message = "Email đã được sử dụng")
    private String email;
    private String phoneNumber;
    @NotNull(message = "Vai trò không được bỏ trống")
    private String role;
}
