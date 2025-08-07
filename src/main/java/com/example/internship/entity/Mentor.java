package com.example.internship.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

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
}
