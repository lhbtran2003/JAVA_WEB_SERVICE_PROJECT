package com.example.internship.service.student;

import com.example.internship.config.exception.NotFoundException;
import com.example.internship.dto.request.student.AddStudentRequest;
import com.example.internship.dto.request.user.AddUserRequest;
import com.example.internship.dto.response.ApiResponse;
import com.example.internship.entity.Student;
import com.example.internship.entity.User;
import com.example.internship.repository.StudentRepository;
import com.example.internship.repository.UserRepository;
import com.example.internship.service.user.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements IStudentService {
    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final IUserService iUserService;

    @Override
    public ApiResponse<Student> createStudent(AddStudentRequest request) {
        User user;
        if (request.getUserId() != null) {
            user = userRepository.findById(request.getUserId()).orElseThrow(() -> new NotFoundException("Không tìm thấy tài khoản có id " + request.getUserId()));
        } else {
            AddUserRequest addUserRequest;
        }
        return null;
    }
}
