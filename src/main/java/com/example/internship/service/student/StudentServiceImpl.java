package com.example.internship.service.student;

import com.example.internship.config.exception.ConflictDataException;
import com.example.internship.config.exception.NotFoundException;
import com.example.internship.dto.request.student.AddStudentRequest;
import com.example.internship.dto.request.student.UpdateStudentRequest;
import com.example.internship.dto.request.user.AddUserRequest;
import com.example.internship.dto.response.ApiResponse;
import com.example.internship.dto.response.FieldErrorResponse;
import com.example.internship.entity.*;
import com.example.internship.mapper.StudentMapper;
import com.example.internship.mapper.UserMapper;
import com.example.internship.repository.InternshipAssignmentRepository;
import com.example.internship.repository.MentorRepository;
import com.example.internship.repository.StudentRepository;
import com.example.internship.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements IStudentService {
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    public final InternshipAssignmentRepository internshipAssignmentRepository;
    public final MentorRepository mentorRepository;
    public final UserRepository userRepository;

    @Override
    public ApiResponse<Student> createStudent(AddStudentRequest request) {
        List<FieldErrorResponse> errorResponses = new ArrayList<>();

        Long countStudentCodeExisted = studentRepository.countAllByStudentCode(request.getStudentCode());
        if (countStudentCodeExisted > 0) {
            errorResponses.add(new FieldErrorResponse("studentCode", "Mã sinh viên đã được sử dụng"));
        }

        Long countUserEmailExisted = userRepository.countByEmail(request.getEmail());
        if (countUserEmailExisted > 0) {
            errorResponses.add(new FieldErrorResponse("email", "Email đã được sử dụng"));
        }

        Long countUsernameExisted = userRepository.countByUsername(request.getUsername());
        if (countUsernameExisted > 0) {
            errorResponses.add(new FieldErrorResponse("username", "Username đã được sử dụng"));
        }

        if (!errorResponses.isEmpty()) {
            throw new ConflictDataException(errorResponses);
        }
        AddUserRequest addUserRequest = StudentMapper.convertToAddUserRequest(request);

        User user = UserMapper.toEntity(addUserRequest, passwordEncoder);

        Student student = StudentMapper.toEntity(request);
        student.setUser(user);

        return ApiResponse.<Student>builder()
                .data(studentRepository.save(student))
                .timestamp(LocalDateTime.now())
                .success(true)
                .build();
    }

    @Override
    public ApiResponse<Student> getStudentById(Integer id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new NotFoundException("Không tìm thấy sinh viên có id " + id));
        return ApiResponse.<Student>builder()
                .success(true)
                .message("Lấy thông tin sinh viên thành công")
                .data(student)
                .build();
    }

    @Override
    public ApiResponse<Student> getStudentByIdForStudent(Integer id, Integer idLogin) {
        if (!Objects.equals(id, idLogin)) {
            throw new NotFoundException("Không tìm thấy tài nguyên");
        }
        Student student = studentRepository.findById(id).get();
        return ApiResponse.<Student>builder()
                .success(true)
                .message("Lấy thông tin thành công")
                .data(student)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @Override
    public ApiResponse<List<Student>> getAllStudents() {
        return ApiResponse.<List<Student>>builder()
                .success(true)
                .message("Lấy danh sách học viên thành công")
                .data(studentRepository.findAll())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @Override
    public ApiResponse<List<Student>> getAllStudentsByMentor(Integer idLogin) {
        Mentor mentor = mentorRepository.findById(idLogin).get(); // tại vì có idLogin => có tài khoản rồi
        List<InternshipAssignment> list = internshipAssignmentRepository.findAllByMentor(mentor);
        List<Student> studentList = list.stream().map(InternshipAssignment::getStudent).toList();
        return ApiResponse.<List<Student>>builder()
                .success(true)
                .message("Lấy danh sách sinh viên do mentor " + mentor.getUser().getFullName() + " hướng dẫn")
                .data(studentList)
                .timestamp(LocalDateTime.now())
                .build();

    }

    @Override
    public ApiResponse<Student> updateStudent(Integer id, UpdateStudentRequest request) {
        if (!studentRepository.existsById(id)) {
            throw new NotFoundException("Tài nguyên không tồn tại");
        }

        List<FieldErrorResponse> errorResponses = new ArrayList<>();
        Long count = studentRepository.countByStudentCodeExcludeStudentId(request.getStudentCode(), id);
        if (count > 0) {
            errorResponses.add(new FieldErrorResponse("studentCode", "Mã sinh viên đã được sử dụng"));
        }

        if (!errorResponses.isEmpty()) {
            throw new ConflictDataException(errorResponses);
        }

        Student studentExisted = studentRepository.findById(id).get();
        Student student = StudentMapper.toEntity(request,studentExisted);
        return ApiResponse.<Student>builder()
                .data(studentRepository.save(student))
                .timestamp(LocalDateTime.now())
                .success(true)
                .message("Cập nhật thông tin cho sinh viên thành công")
                .build();
    }

    @Override
    public ApiResponse<Student> updateStudentByStudent(Integer id, Integer idLogin, UpdateStudentRequest request) {
        if (!idLogin.equals(id)) {
            throw new NotFoundException("Ko có quyền truy cập");
        }
        List<FieldErrorResponse> errorResponses = new ArrayList<>();
        Long count = studentRepository.countByStudentCodeExcludeStudentId(request.getStudentCode(), id);
        if (count > 0) {
            errorResponses.add(new FieldErrorResponse("studentCode", "Mã sinh viên đã được sử dụng"));
        }

        if (!errorResponses.isEmpty()) {
            throw new ConflictDataException(errorResponses);
        }

        Student studentExisted = studentRepository.findById(id).get();
        Student student = StudentMapper.toEntity(request,studentExisted);
        return ApiResponse.<Student>builder()
                .data(studentRepository.save(student))
                .timestamp(LocalDateTime.now())
                .success(true)
                .message("Cập nhật thông tin cho sinh viên thành công")
                .build();
    }
}
