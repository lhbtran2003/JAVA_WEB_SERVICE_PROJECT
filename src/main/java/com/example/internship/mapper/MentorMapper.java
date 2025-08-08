package com.example.internship.mapper;

import com.example.internship.dto.request.mentor.AddMentorRequest;
import com.example.internship.dto.request.mentor.MentorDto;
import com.example.internship.dto.request.mentor.UpdateMentorRequest;
import com.example.internship.dto.request.student.AddStudentRequest;
import com.example.internship.dto.request.user.AddUserRequest;
import com.example.internship.entity.Mentor;

public class MentorMapper {
    public static MentorDto toDto(Mentor mentor) {
        MentorDto mentorDto = new MentorDto();
        mentorDto.setFullName(mentorDto.getFullName());
        mentorDto.setEmail(mentorDto.getEmail());
        mentorDto.setPhoneNumber(mentorDto.getPhoneNumber());
        return mentorDto;
    }

    public static AddUserRequest convertToAddUserRequest(AddMentorRequest mentorRequest) {
        AddUserRequest userRequest = new AddUserRequest();
        userRequest.setUsername(mentorRequest.getUsername());
        userRequest.setEmail(mentorRequest.getEmail());
        userRequest.setFullName(mentorRequest.getFullName());
        userRequest.setPhoneNumber(mentorRequest.getPhoneNumber());
        userRequest.setPassword(mentorRequest.getPassword());
        userRequest.setRole("mentor");
        return userRequest;
    }

    public static Mentor toEntity(AddMentorRequest request) {
        Mentor mentor = new Mentor();
        mentor.setAcademicRank(request.getAcademicRank());
        mentor.setDepartment(request.getDepartment());
        return  mentor;
    }

    public static Mentor toEntity(UpdateMentorRequest mentorRequest, Mentor mentorExisted) {
        mentorExisted.setDepartment(mentorRequest.getDepartment());
        mentorExisted.setAcademicRank(mentorRequest.getAcademicRank());
        return mentorExisted;
    }
}
