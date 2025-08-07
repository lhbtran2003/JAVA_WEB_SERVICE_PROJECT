package com.example.internship.repository;

import com.example.internship.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student,Integer> {
   @Query("SELECT s FROM Student s WHERE s.studentId = :id AND s.user.role = 'ROLE_USER'")
   Optional<Student> getStudentByIdForStudent(Integer id);
}
