package com.example.internship.dto.request.assessmentround;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AddAssessmentRound {
    @NotNull(message = "Id của giai đoạn thưc tập không được bỏ trống")
    private Integer internshipPhaseId;
    @NotBlank(message = "Tên đợt đánh giá không được bỏ trống")
    private String roundName;
    @NotBlank(message = "Ngày bắt đầu không được bỏ trống")
    private String startDate;
    @NotBlank(message = "Ngày kết thúc không được bỏ trống")
    private String endDate;
    private String description;

}
