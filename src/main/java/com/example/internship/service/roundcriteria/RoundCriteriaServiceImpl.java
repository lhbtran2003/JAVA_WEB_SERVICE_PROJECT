package com.example.internship.service.roundcriteria;

import com.example.internship.config.exception.ConflictDataException;
import com.example.internship.config.exception.NotFoundException;
import com.example.internship.dto.request.roundcriteria.AddRoundCriteria;
import com.example.internship.dto.request.roundcriteria.UpdateRoundCriteria;
import com.example.internship.dto.response.ApiResponse;
import com.example.internship.dto.response.FieldErrorResponse;
import com.example.internship.entity.AssessmentRound;
import com.example.internship.entity.EvaluationCriteria;
import com.example.internship.entity.RoundCriteria;
import com.example.internship.mapper.RoundCriteriaMapper;
import com.example.internship.repository.AssessmentRoundRepository;
import com.example.internship.repository.EvaluationCriteriaRepository;
import com.example.internship.repository.RoundCriteriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class RoundCriteriaServiceImpl implements IRoundCriteriaService {
    private final RoundCriteriaRepository roundCriteriaRepository;
    private final AssessmentRoundRepository assessmentRoundRepository;
    private final EvaluationCriteriaRepository evaluationCriteriaRepository;

    @Override
    public ApiResponse<List<RoundCriteria>> findAll() {
        return ApiResponse.<List<RoundCriteria>>builder()
                .success(true)
                .message("Lấy danh sách các tiêu chí cụ thể của các đợt đánh giá")
                .data(roundCriteriaRepository.findAll())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @Override
    public ApiResponse<RoundCriteria> findById(Integer id) {
        RoundCriteria roundCriteria = roundCriteriaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tài nguyên không tồn tại"));
        return ApiResponse.<RoundCriteria>builder()
                .success(true)
                .message("Lấy tiêu chí cụ thể thành công")
                .data(roundCriteria)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @Override
    public ApiResponse<RoundCriteria> create(AddRoundCriteria request) {
        AssessmentRound assessmentRound = assessmentRoundRepository.findById(request.getAssessmentRoundId())
                .orElseThrow(() -> new  NotFoundException("Không tồn tại đợt đánh giá này với id = " + request.getAssessmentRoundId()));
        EvaluationCriteria evaluationCriteria = evaluationCriteriaRepository.findById(request.getEvaluationCriteriaId())
                .orElseThrow(() -> new NotFoundException("Không tồn tại tiêu chí đánh giá với id = " + request.getEvaluationCriteriaId()));
        Long count = roundCriteriaRepository.countByRoundAndCriterion(assessmentRound, evaluationCriteria);

        List<FieldErrorResponse> errorResponses = new ArrayList<>();
        if (count > 0) {
            errorResponses.add(new FieldErrorResponse("UNIQUE","Tiêu chí đã tồn tại trong đợt đánh giá này"));
        }
        if (!errorResponses.isEmpty()) {
            throw new ConflictDataException(errorResponses);
        }
        RoundCriteria roundCriteria = RoundCriteriaMapper.toEntity(request);
        roundCriteria.setAssessmentRound(assessmentRound);
        roundCriteria.setEvaluationCriteria(evaluationCriteria);
        return ApiResponse.<RoundCriteria>builder()
                .success(true)
                .timestamp(LocalDateTime.now())
                .data(roundCriteriaRepository.save(roundCriteria))
                .message("Thêm 1 tiêu chí cho đợt đánh giá '"+assessmentRound.getRoundName()+"' thành công")
                .build();
    }

    @Override
    public ApiResponse<RoundCriteria> update(Integer id, UpdateRoundCriteria request) {
        if (!roundCriteriaRepository.existsById(id)) {
            throw new NotFoundException("Tài nguyên không tồn tại");
        }

        RoundCriteria roundCriteriaExisted = roundCriteriaRepository.findById(id).get();
        RoundCriteria roundCriteria = RoundCriteriaMapper.toEntity(roundCriteriaExisted,request);

        return ApiResponse.<RoundCriteria>builder()
                .success(true)
                .timestamp(LocalDateTime.now())
                .data(roundCriteriaRepository.save(roundCriteria))
                .message("Cập nhật trọng số thành công")
                .build();
    }

    @Override
    public ApiResponse<Void> deleteById(Integer id) {
        if (!roundCriteriaRepository.existsById(id)) {
            throw new NotFoundException("Tài nguyên không tồn tại");
        }
        roundCriteriaRepository.deleteById(id);
        return ApiResponse.<Void>builder()
                .success(true)
                .timestamp(LocalDateTime.now())
                .message("Xóa 1 tiêu chí cho đợt đánh giá thành công")
                .build();
    }
}
