package com.example.internship.dto.request.student;

import com.example.internship.dto.request.user.AddUserRequest;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AddStudentRequest {
    private Integer userId;
    @NotBlank(message = "Mã sinh viên không được bỏ trống")
    private String studentCode;
    private String major;
    private String className;
    private LocalDate dateOfBirth;
    private String address;
    @NotNull(message = "Username không được bỏ trống")
    private String username;
    @NotNull(message = "Mật khẩu không được bỏ trống")
    private String password;
    @NotNull(message = "Tên đầy đủ không được bỏ trống")
    private String fullName;
    @NotNull(message = "Email không được bỏ trống")
    @Email(message = "Email không đúng định dạng")
    private String email;
    private String phoneNumber;
}
