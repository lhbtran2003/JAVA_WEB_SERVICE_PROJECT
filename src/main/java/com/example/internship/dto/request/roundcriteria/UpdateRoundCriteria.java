package com.example.internship.dto.request.roundcriteria;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class UpdateRoundCriteria {
    @NotNull(message = "Trọng số không được bỏ trống")
    private BigDecimal weight; // trọng số (vd: 0.2, 0.3, ...)
}
