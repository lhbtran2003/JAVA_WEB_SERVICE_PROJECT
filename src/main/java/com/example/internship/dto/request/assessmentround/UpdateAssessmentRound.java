package com.example.internship.dto.request.assessmentround;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateAssessmentRound {
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
