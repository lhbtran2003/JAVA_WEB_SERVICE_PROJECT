package com.example.internship.repository;

import com.example.internship.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student,Integer> {
   Long countAllByStudentCode (String studentCode);
   @Query("SELECT COUNT(s) FROM Student s WHERE s.studentCode = :studentCode AND s.studentId <> :id")
   Long countByStudentCodeExcludeStudentId (@Param("studentCode") String studentCode,  @Param("id") Integer id);
}
