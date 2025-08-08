package com.example.internship.mapper;

import com.example.internship.dto.request.student.AddStudentRequest;
import com.example.internship.dto.request.student.UpdateStudentRequest;
import com.example.internship.dto.request.user.AddUserRequest;
import com.example.internship.entity.Student;

public class StudentMapper {
    public static Student toEntity(AddStudentRequest request){
        Student student = new Student();
        student.setStudentCode(request.getStudentCode());
        student.setMajor(request.getMajor());
        student.setAddress(request.getAddress());
        student.setDateOfBirth(request.getDateOfBirth());
        student.setClassName(request.getClassName());
        return student;
    }

    public static AddUserRequest convertToAddUserRequest(AddStudentRequest studentRequest) {
        AddUserRequest userRequest = new AddUserRequest();
        userRequest.setUsername(studentRequest.getUsername());
        userRequest.setEmail(studentRequest.getEmail());
        userRequest.setFullName(studentRequest.getFullName());
        userRequest.setPhoneNumber(studentRequest.getPhoneNumber());
        userRequest.setPassword(studentRequest.getPassword());
        userRequest.setRole("student");
        return userRequest;
    }

    public static Student toEntity(UpdateStudentRequest request, Student studentExisted){
        studentExisted.setStudentCode(request.getStudentCode());
        studentExisted.setMajor(request.getMajor());
        studentExisted.setAddress(request.getAddress());
        studentExisted.setDateOfBirth(request.getDateOfBirth());
        studentExisted.setClassName(request.getClassName());
        return studentExisted;
    }

}
