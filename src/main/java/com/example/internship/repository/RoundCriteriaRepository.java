package com.example.internship.repository;

import com.example.internship.entity.AssessmentRound;
import com.example.internship.entity.EvaluationCriteria;
import com.example.internship.entity.RoundCriteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoundCriteriaRepository extends JpaRepository<RoundCriteria,Integer> {
    @Query("SELECT COUNT(rc) FROM RoundCriteria rc WHERE rc.assessmentRound = :assessmentRound AND rc.evaluationCriteria = :evaluationCriteria")
    Long countByRoundAndCriterion (AssessmentRound  assessmentRound, EvaluationCriteria  evaluationCriteria);

    @Query("SELECT COUNT(rc) FROM RoundCriteria rc WHERE (rc.assessmentRound = :assessmentRound AND rc.evaluationCriteria = :evaluationCriteria) AND rc.id <> :id")
    Long countByRoundAndCriterionExcludeId (AssessmentRound  assessmentRound, EvaluationCriteria  evaluationCriteria, Integer id);

}
