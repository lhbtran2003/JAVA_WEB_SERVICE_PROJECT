package com.example.internship.repository;

import com.example.internship.entity.AssessmentResults;
import com.example.internship.entity.AssessmentRound;
import com.example.internship.entity.EvaluationCriteria;
import com.example.internship.entity.InternshipAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AssessmentResultRepository extends JpaRepository<AssessmentResults, Integer> {
     List<AssessmentResults> findByInternshipAssignment_Id(Integer id);

     @Query("SELECT ar FROM AssessmentResults ar WHERE ar.internshipAssignment.mentor.mentorId = :id")
     List<AssessmentResults> findByMentorId(Integer id);

     @Query("SELECT ar FROM AssessmentResults ar WHERE ar.internshipAssignment.student.studentId = :id")
     List<AssessmentResults> findByStudentId(Integer id);

     @Query("SELECT COUNT(ar) FROM AssessmentResults ar " +
             "WHERE ar.internshipAssignment = :internshipAssignment " +
             "AND ar.evaluationCriteria = :evaluationCriteria " +
             "AND ar.assessmentRound = :assessmentRound")
     Long countByUnique (InternshipAssignment internshipAssignment,
                         EvaluationCriteria evaluationCriteria,
                         AssessmentRound assessmentRound);
}
