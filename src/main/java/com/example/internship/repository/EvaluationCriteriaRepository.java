package com.example.internship.repository;

import com.example.internship.entity.EvaluationCriteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EvaluationCriteriaRepository extends JpaRepository<EvaluationCriteria,Integer> {
    Long countByCriterionName(String criterionName);

    @Query("SELECT COUNT(e) FROM EvaluationCriteria e WHERE e.criterionName = :criterionName AND e.criterionId <> :id")
    Long countByCriterionNameExcludeId (String criterionName, Integer id);
}
