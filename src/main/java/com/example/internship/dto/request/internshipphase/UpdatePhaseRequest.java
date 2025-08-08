package com.example.internship.dto.request.internshipphase;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePhaseRequest {
    @NotBlank(message = "Tên giai đoạn thực tập không được để trống")
    private String phaseName;
    @NotBlank(message = "Ngày bắt đầu không được để trống")
    private String startDate;
    @NotBlank(message = "Ngày kết thúc không được để trống")
    private String endDate;
    private String description;
}
