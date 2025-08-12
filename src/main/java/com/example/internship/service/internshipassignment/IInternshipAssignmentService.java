package com.example.internship.service.internshipassignment;

import com.example.internship.dto.request.internshipassignment.AddInternshipAssignment;
import com.example.internship.dto.request.internshipassignment.UpdateInternshipAssignment;
import com.example.internship.dto.response.ApiResponse;
import com.example.internship.entity.InternshipAssignment;
import com.example.internship.entity.Mentor;
import com.example.internship.entity.Student;

import java.util.List;

public interface IInternshipAssignmentService {
    ApiResponse<List<InternshipAssignment>> findAllByAdmin();
    ApiResponse<List<InternshipAssignment>> findAllByMentor( Integer idLogin);
    ApiResponse<List<InternshipAssignment>> findAllByStudent(Integer idLogin);

    ApiResponse<InternshipAssignment>  findById(Integer id);
    ApiResponse<InternshipAssignment> findByIdByMentor(Integer id, Integer idLogin);
    ApiResponse<InternshipAssignment> findByIdByStudent(Integer id, Integer idLogin);

    ApiResponse<InternshipAssignment> create(AddInternshipAssignment request);

    ApiResponse<InternshipAssignment> update(Integer id, UpdateInternshipAssignment request);

}
