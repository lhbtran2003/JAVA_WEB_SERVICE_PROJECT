package com.example.internship.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "mentors")
public class Mentor {
    @Id
    @Column(name = "mentor_id")
    private Integer mentorId;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "mentor_id")
    @Where(clause = "role = 'ROLE_MENTOR'")
    private User user;

    @Column(length = 100)
    private String department;

    @Column(length = 50)
    private String academicRank;

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
