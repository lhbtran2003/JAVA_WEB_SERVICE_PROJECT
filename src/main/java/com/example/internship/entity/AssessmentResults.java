package com.example.internship.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "assessment_result",
        uniqueConstraints = @UniqueConstraint(columnNames = {"assessment_round_id", "criterion_id","assignment_id"})
)
public class AssessmentResults {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assessment_result_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "assessment_round_id",nullable = false)
    private AssessmentRound assessmentRound;

    @ManyToOne
    @JoinColumn(name = "assignment_id",nullable = false)
    private InternshipAssignment internshipAssignment;

    @ManyToOne
    @JoinColumn(name = "criterion_id",nullable = false)
    private EvaluationCriteria evaluationCriteria;

    @Column(name = "score", nullable = false, columnDefinition = "DECIMAL(5,2) CHECK (score >= 0)")
    private BigDecimal score;

    private String comments;

    @ManyToOne
    @JoinColumn(name = "mentor_id", nullable = false)
    private Mentor mentor;

}
