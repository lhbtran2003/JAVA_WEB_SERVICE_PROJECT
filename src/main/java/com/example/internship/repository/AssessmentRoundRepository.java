package com.example.internship.repository;

import com.example.internship.entity.AssessmentRound;
import com.example.internship.entity.InternshipPhase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssessmentRoundRepository extends JpaRepository<AssessmentRound,Integer> {
    boolean existsByInternshipPhase(InternshipPhase internshipPhase);
    List<AssessmentRound> findByInternshipPhase(InternshipPhase internshipPhase);
}
