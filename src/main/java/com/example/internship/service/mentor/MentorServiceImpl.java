package com.example.internship.service.mentor;

import com.example.internship.config.exception.NotFoundException;
import com.example.internship.dto.response.ApiResponse;
import com.example.internship.entity.Mentor;
import com.example.internship.repository.MentorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MentorServiceImpl implements IMentorService {

    private final MentorRepository mentorRepository;

    @Override
    public ApiResponse<List<Mentor>> getAllMentorsForAdmin() {
        return ApiResponse.<List<Mentor>>builder()
                .success(true)
                .timestamp(LocalDateTime.now())
                .message("Lấy danh sách giảng viên thành công")
                .data(mentorRepository.findAll())
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
}
