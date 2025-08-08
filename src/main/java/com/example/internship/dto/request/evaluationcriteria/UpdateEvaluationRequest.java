package com.example.internship.dto.request.evaluationcriteria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class UpdateEvaluationRequest {
    @NotBlank(message = "Tên tiêu chí đánh giá không được bỏ trống")
    private String criterionName; //Tên tiêu chí đánh giá (ví dụ: "Thái độ làm việc").
    private String description;
    @NotNull(message = "Điểm tối đa không được bỏ trống")
    private BigDecimal maxScore; //Điểm tối đa cho tiêu chí này.
}
