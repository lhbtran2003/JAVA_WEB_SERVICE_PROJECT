package com.example.internship.service.mentor;

import com.example.internship.dto.response.ApiResponse;
import com.example.internship.entity.Mentor;

import java.util.List;
import java.util.Optional;

public interface IMentorService {
    ApiResponse<List<Mentor>> getAllMentorsForAdmin();
    ApiResponse<Mentor> getMentorById(Integer id);
}
