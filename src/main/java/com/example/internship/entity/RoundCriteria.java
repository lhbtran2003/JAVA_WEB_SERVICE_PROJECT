package com.example.internship.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;


// Bảng trung gian này liên kết AssessmentRounds với EvaluationCriteria,
// cho phép mỗi đợt đánh giá có một bộ tiêu chí riêng với trọng số khác nhau.


@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "round_criteria")
public class RoundCriteria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "round_criteria_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "assessment_round_id", nullable = false)
    private AssessmentRound assessmentRound;

    @ManyToOne
    @JoinColumn(name = "criterion_id", nullable = false)
    private EvaluationCriteria evaluationCriteria;

    @Column( nullable = false, columnDefinition = "DECIMAL(5,2) CHECK (weight > 0)")
    private BigDecimal weight; // trọng số (vd: 0.2, 0.3, ...)

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
