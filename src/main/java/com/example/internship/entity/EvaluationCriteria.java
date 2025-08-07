package com.example.internship.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

// Tiêu chí đánh giá

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "evaluation_criteria")
public class EvaluationCriteria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "criterion_id")
    private Integer criterionId;

    @Column(name = "criterion_name", length = 200, unique = true, nullable = false)
    private String criterionName; //Tên tiêu chí đánh giá (ví dụ: "Thái độ làm việc").

    private String description;

    @Column(name = "max_score", nullable = false, columnDefinition = "DECIMAL(5,2) CHECK (max_score > 0)")
    private BigDecimal maxScore; //Điểm tối đa cho tiêu chí này.

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    public void beforeCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void beforeUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
