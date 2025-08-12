package com.example.internship.dto.request.assessmentresult;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class UpdateAssessmentResult {
    @NotNull(message = "Không được bỏ trống")
    private BigDecimal score;
    private String comments;
}
