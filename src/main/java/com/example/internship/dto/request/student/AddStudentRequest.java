package com.example.internship.dto.request.student;

import com.example.internship.dto.request.user.AddUserRequest;
import jakarta.validation.constraints.NotBlank;
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
    private AddUserRequest addUserRequest;
}
