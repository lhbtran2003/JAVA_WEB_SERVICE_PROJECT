package com.example.internship.dto.request.assessmentresult;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AddAssessmentResult {
    @NotNull(message = "Không được bỏ trống")
    private Integer assessmentRoundId;
    @NotNull(message = "Không được bỏ trống")
    private Integer internshipAssignmentId;
    @NotNull(message = "Không được bỏ trống")
    private Integer evaluationCriteriaId;
    @NotNull(message = "Không được bỏ trống")
    private BigDecimal score;
    private String comments;

}
