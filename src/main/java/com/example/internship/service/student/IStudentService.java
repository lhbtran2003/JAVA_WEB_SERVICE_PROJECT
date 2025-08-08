package com.example.internship.service.student;

import com.example.internship.dto.request.student.AddStudentRequest;
import com.example.internship.dto.request.student.UpdateStudentRequest;
import com.example.internship.dto.response.ApiResponse;
import com.example.internship.entity.Mentor;
import com.example.internship.entity.Student;

import java.util.List;

public interface IStudentService {
    ApiResponse<Student> createStudent(AddStudentRequest request);
    ApiResponse<Student> getStudentById(Integer id); // cho phép admin, mentor truy cập
    ApiResponse<Student> getStudentByIdForStudent(Integer id, Integer idLogin);
    ApiResponse<List<Student>> getAllStudents();
    ApiResponse<List<Student>> getAllStudentsByMentor(Integer idLogin);
    ApiResponse<Student> updateStudent (Integer id, UpdateStudentRequest request);
    ApiResponse<Student> updateStudentByStudent(Integer id, Integer idLogin, UpdateStudentRequest request);
}
