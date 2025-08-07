package com.example.internship.service.student;

import com.example.internship.dto.request.student.AddStudentRequest;
import com.example.internship.dto.request.user.UpdateUserRequest;
import com.example.internship.dto.response.ApiResponse;
import com.example.internship.entity.Student;

import java.util.List;

public interface IStudentService {
    ApiResponse<Student> createStudent(AddStudentRequest request);
    ApiResponse<Student> getStudentById(Integer id);
    ApiResponse<Student> getStudentByIdForStudent(Integer id, Integer idLogin);
    ApiResponse<List<Student>> getAllStudentsForAdmin();
}
