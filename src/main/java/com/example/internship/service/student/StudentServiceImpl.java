package com.example.internship.service.student;

import com.example.internship.config.exception.NotFoundException;
import com.example.internship.dto.request.student.AddStudentRequest;
import com.example.internship.dto.request.user.AddUserRequest;
import com.example.internship.dto.response.ApiResponse;
import com.example.internship.entity.RoleName;
import com.example.internship.entity.Student;
import com.example.internship.entity.User;
import com.example.internship.repository.StudentRepository;
import com.example.internship.repository.UserRepository;
import com.example.internship.service.user.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements IStudentService {
    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final IUserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ApiResponse<Student> createStudent(AddStudentRequest request) {

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setRole(RoleName.ROLE_STUDENT);
        user.setPhoneNumber(request.getPhoneNumber());
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));

        Student student = new Student();
        student.setStudentCode(request.getStudentCode());
        student.setMajor(request.getMajor());
        student.setAddress(request.getAddress());
        student.setDateOfBirth(request.getDateOfBirth());
        student.setClassName(request.getClassName());
        student.setUser(user);

        return ApiResponse.<Student>builder()
                .data(studentRepository.save(student))
                .timestamp(LocalDateTime.now())
                .success(true)
                .build();
    }

    @Override
    public ApiResponse<Student> getStudentById(Integer id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new NotFoundException("Không tìm thấy sinh viên có id "+id));
        return ApiResponse.<Student>builder()
                .success(true)
                .message("Lấy thông tin sinh viên thành công")
                .data(student)
                .build();
    }

    @Override
    public ApiResponse<Student> getStudentByIdForStudent(Integer id, Integer idLogin) {
        if (!Objects.equals(id, idLogin)) {
            throw  new NotFoundException("Không tìm thấy tài nguyên");
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
    public ApiResponse<List<Student>> getAllStudentsForAdmin() {
        return ApiResponse.<List<Student>>builder()
                .success(true)
                .message("Lấy danh sách học viên thành công")
                .data(studentRepository.findAll())
                .timestamp(LocalDateTime.now())
                .build();
    }
}
