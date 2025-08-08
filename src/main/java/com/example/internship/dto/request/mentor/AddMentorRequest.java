package com.example.internship.dto.request.mentor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddMentorRequest {
    @Size(min = 1, max = 100)
    private String department;
    @Size(min = 1, max = 100)
    private String academicRank;

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
