package com.example.internship.service.student;

import com.example.internship.dto.request.student.AddStudentRequest;
import com.example.internship.dto.response.ApiResponse;
import com.example.internship.entity.Student;

public interface IStudentService {
    ApiResponse<Student> createStudent(AddStudentRequest request);
}
