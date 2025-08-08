package com.example.internship.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

// Bảng này quản lý các giai đoạn thực tập (ví dụ: "Thực tập cơ sở 1", "Thực tập tốt nghiệp").

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "internship_phases")
public class InternshipPhase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "phase_id")
    private Integer phaseId;

    @Column(name = "phase_name", length = 100, nullable = false, unique = true)
    private String phaseName;

    @Column(nullable = false)
    private LocalDate startDate;
    @Column(nullable = false)
    private LocalDate endDate;

    private String description;

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
