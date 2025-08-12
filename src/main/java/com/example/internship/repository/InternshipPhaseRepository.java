package com.example.internship.repository;

import com.example.internship.entity.InternshipPhase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface InternshipPhaseRepository extends JpaRepository<InternshipPhase, Integer> {
    Long countInternshipPhaseByPhaseName(String phaseName);

    @Query("SELECT COUNT(p) FROM InternshipPhase p WHERE p.phaseName = :name AND p.phaseId <> :id")
    Long countPhaseExcludeId(String name, Integer id);

    Optional<InternshipPhase> findByPhaseNameContainingIgnoreCase(String phaseName);
}
