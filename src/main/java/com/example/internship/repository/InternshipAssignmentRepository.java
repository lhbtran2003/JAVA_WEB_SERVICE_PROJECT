package com.example.internship.repository;

import com.example.internship.entity.InternshipAssignment;
import com.example.internship.entity.Mentor;
import com.example.internship.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InternshipAssignmentRepository extends JpaRepository<InternshipAssignment, Integer> {
    List<InternshipAssignment> findAllByMentor(Mentor mentor);
}
