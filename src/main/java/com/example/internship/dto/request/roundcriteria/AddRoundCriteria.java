package com.example.internship.dto.request.roundcriteria;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class AddRoundCriteria {
    @NotNull(message = "Id của đợt đánh giá không được bỏ trống")
    private Integer assessmentRoundId;
    @NotNull(message = "Id của tiêu chí đánh giá không được bỏ trống")
    private Integer evaluationCriteriaId;
    @NotNull(message = "Trọng số không được bỏ trống")
    private BigDecimal weight; // trọng số (vd: 0.2, 0.3, ...)

}
