package com.example.internship.dto.request.user;

import com.example.internship.utils.validation.add.UniqueEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddUserRequest {
    @NotBlank(message = "Username không được bỏ trống")
    private String username;
    @NotBlank(message = "Mật khẩu không được bỏ trống")
    private String password;
    @NotBlank(message = "Tên đầy đủ không được bỏ trống")
    private String fullName;
    @NotBlank(message = "Email không được bỏ trống")
    @Email(message = "Email không đúng định dạng")
    private String email;
    private String phoneNumber;
    @NotBlank(message = "Vai trò không được bỏ trống")
    private String role;
}
