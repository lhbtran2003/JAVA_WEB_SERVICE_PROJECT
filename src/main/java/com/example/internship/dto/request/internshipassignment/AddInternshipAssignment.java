package com.example.internship.dto.request.internshipassignment;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AddInternshipAssignment {
    @NotNull(message = "Mã sinh viên không được bỏ trống")
    private Integer studentId;
    @NotNull(message = "Mã giảng viên không được bỏ trống")
    private Integer mentorId;
    @NotNull(message = "Mã giai đoạn thực tập không được bỏ trống")
    private Integer phaseId;
    private String assignedDate;
    private String status;
}
