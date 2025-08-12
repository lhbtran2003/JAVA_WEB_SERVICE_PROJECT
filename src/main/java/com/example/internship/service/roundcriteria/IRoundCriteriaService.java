package com.example.internship.service.roundcriteria;

import com.example.internship.dto.request.roundcriteria.AddRoundCriteria;
import com.example.internship.dto.request.roundcriteria.UpdateRoundCriteria;
import com.example.internship.dto.response.ApiResponse;
import com.example.internship.entity.RoundCriteria;

import java.util.List;

public interface IRoundCriteriaService {
    ApiResponse<List<RoundCriteria>> findAll();
    ApiResponse<RoundCriteria> findById(Integer id);
    ApiResponse<RoundCriteria> create (AddRoundCriteria request);
    ApiResponse<RoundCriteria> update(Integer id, UpdateRoundCriteria request);
    ApiResponse<Void> deleteById(Integer id);
}
