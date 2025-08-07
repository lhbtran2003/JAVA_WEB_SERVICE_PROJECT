package com.example.internship.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;


// Bảng này quản lý các đợt đánh giá cụ thể thuộc một giai đoạn thực tập.

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "assessment_round")
public class AssessmentRound {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assessment_round_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "phase_id")
    private InternshipPhase internshipPhase;

    @Column(name = "round_name", nullable = false)
    private String roundName;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    private String description;

    private boolean isActive = true;

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
