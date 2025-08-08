package com.example.internship.service.mentor;

import com.example.internship.dto.request.mentor.AddMentorRequest;
import com.example.internship.dto.request.mentor.MentorDto;
import com.example.internship.dto.request.mentor.UpdateMentorRequest;
import com.example.internship.dto.response.ApiResponse;
import com.example.internship.entity.Mentor;

import java.util.List;

public interface IMentorService {
    ApiResponse<List<Mentor>> getAllMentors();
    ApiResponse<List<MentorDto>> getAllMentorsForStudent();
    ApiResponse<Mentor> getMentorById(Integer id);
    ApiResponse<Mentor> getMentorByIdForMentor(Integer id, Integer idLogin);
    ApiResponse<Mentor> createMentor(AddMentorRequest request);
    ApiResponse<Mentor> updateMentor(Integer id, UpdateMentorRequest request);
    ApiResponse<Mentor> updateMentorByMentor(Integer id, Integer idLogin, UpdateMentorRequest request);
}
