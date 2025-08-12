package com.example.internship.repository;

import com.example.internship.entity.InternshipAssignment;
import com.example.internship.entity.InternshipPhase;
import com.example.internship.entity.Mentor;
import com.example.internship.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface InternshipAssignmentRepository extends JpaRepository<InternshipAssignment, Integer> {
    List<InternshipAssignment> findAllByMentor(Mentor mentor);
    List<InternshipAssignment> findAllByStudent(Student student);

    @Query("SELECT ir FROM InternshipAssignment ir WHERE ir.id = :id AND (ir.mentor.user.role = 'ROLE_MENTOR' AND ir.mentor.user.userId = :userId)")
    Optional<InternshipAssignment> findByIdByMentor (Integer id, Integer userId);

    @Query("SELECT ir FROM InternshipAssignment ir WHERE ir.id = :id AND (ir.student.user.role = 'ROLE_STUDENT' AND ir.student.user.userId = :userId)")
    Optional<InternshipAssignment> findByIdByStudent (Integer id,  Integer userId);

    Long countByStudentAndInternshipPhase(Student student, InternshipPhase internshipPhase);

    Long countByStudentAndInternshipPhaseAndId(Student student, InternshipPhase internshipPhase, Integer id);
}

