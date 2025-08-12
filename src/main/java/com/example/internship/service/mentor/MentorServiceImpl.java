package com.example.internship.service.mentor;

import com.example.internship.config.exception.ConflictDataException;
import com.example.internship.config.exception.NotFoundException;
import com.example.internship.dto.request.mentor.AddMentorRequest;
import com.example.internship.dto.request.mentor.MentorDto;
import com.example.internship.dto.request.mentor.UpdateMentorRequest;
import com.example.internship.dto.request.user.AddUserRequest;
import com.example.internship.dto.response.ApiResponse;
import com.example.internship.dto.response.FieldErrorResponse;
import com.example.internship.entity.Mentor;
import com.example.internship.entity.User;
import com.example.internship.mapper.MentorMapper;
import com.example.internship.mapper.UserMapper;
import com.example.internship.repository.MentorRepository;
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
public class MentorServiceImpl implements IMentorService {

    private final MentorRepository mentorRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public ApiResponse<List<Mentor>> getAllMentors() {
        return ApiResponse.<List<Mentor>>builder()
                .success(true)
                .timestamp(LocalDateTime.now())
                .message("Lấy danh sách giảng viên thành công")
                .data(mentorRepository.findAll())
                .build();
    }

    @Override
    public ApiResponse<List<MentorDto>> getAllMentorsForStudent() {
        List<MentorDto> mentorDtoList = mentorRepository.findAll().stream().map(MentorMapper::toDto).toList();
        return ApiResponse.<List<MentorDto>>builder()
                .message("Lấy dánh sách giảng viên hiển thị cho sinh viên thành công")
                .success(true)
                .data(mentorDtoList)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @Override
    public ApiResponse<Mentor> getMentorById(Integer id) {
        Mentor mentor = mentorRepository.findById(id).orElseThrow(() -> new NotFoundException("Mentor not found"));

        return ApiResponse.<Mentor>builder()
                .success(true)
                .timestamp(LocalDateTime.now())
                .data(mentor)
                .message("Lấy thông tin giảng viên thành công")
                .build();
    }

    @Override
    public ApiResponse<Mentor> getMentorByIdForMentor(Integer id, Integer idLogin) {
        if (!Objects.equals(id, idLogin)) {
            throw new NotFoundException("Không có quyền truy cập tài nguyên này");
        }
        Mentor mentor = mentorRepository.findById(id).get();
        return ApiResponse.<Mentor>builder()
                .success(true)
                .message("Lấy thông tin thành công")
                .data(mentor)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @Override
    public ApiResponse<Mentor> createMentor(AddMentorRequest request) {
        List<FieldErrorResponse> errorResponses = new ArrayList<>();

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

        AddUserRequest addUserRequest = MentorMapper.convertToAddUserRequest(request);
        User user = UserMapper.toEntity(addUserRequest,passwordEncoder);
        Mentor mentor = MentorMapper.toEntity(request);
        mentor.setUser(user);
        return ApiResponse.<Mentor>builder()
                .success(true)
                .message("Thêm tài khoản giảng viên thành công")
                .data(mentorRepository.save(mentor))
                .timestamp(LocalDateTime.now())
                .build();
    }

    @Override
    public ApiResponse<Mentor> updateMentor(Integer id, UpdateMentorRequest request) {
        if(!mentorRepository.existsById(id)){
            throw new NotFoundException("Mentor not found");
        }
        Mentor mentorExisted = mentorRepository.findById(id).get();
        Mentor mentor = MentorMapper.toEntity(request,mentorExisted);
        return ApiResponse.<Mentor>builder()
                .success(true)
                .timestamp(LocalDateTime.now())
                .data(mentorRepository.save(mentor))
                .message("Cập nhật thông tin giảng viên thành công")
                .build();
    }

    @Override
    public ApiResponse<Mentor> updateMentorByMentor(Integer id, Integer idLogin, UpdateMentorRequest request) {
        if(!idLogin.equals(id)){
            throw new NotFoundException("Ko có quyền truy cập");
        }
        Mentor mentorExisted = mentorRepository.findById(id).get();
        Mentor mentor = MentorMapper.toEntity(request,mentorExisted);
        return ApiResponse.<Mentor>builder()
                .success(true)
                .timestamp(LocalDateTime.now())
                .data(mentorRepository.save(mentor))
                .message("Cập nhật thông tin giảng viên thành công")
                .build();
    }
}
