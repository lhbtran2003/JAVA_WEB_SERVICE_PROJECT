package com.example.internship.service.evaluationcriteria;

import com.example.internship.config.exception.ConflictDataException;
import com.example.internship.config.exception.NotFoundException;
import com.example.internship.dto.request.evaluationcriteria.AddEvaluationRequest;
import com.example.internship.dto.request.evaluationcriteria.UpdateEvaluationRequest;
import com.example.internship.dto.response.ApiResponse;
import com.example.internship.dto.response.FieldErrorResponse;
import com.example.internship.entity.EvaluationCriteria;
import com.example.internship.mapper.EvaluationMapper;
import com.example.internship.repository.EvaluationCriteriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EvaluationCriteriaServiceImpl implements IEvaluationCriteriaService {
    private final EvaluationCriteriaRepository evaluationCriteriaRepository;

    @Override
    public ApiResponse<List<EvaluationCriteria>> findAllEvaluationCriterias() {
        return ApiResponse.<List<EvaluationCriteria>>builder()
                .success(true)
                .timestamp(LocalDateTime.now())
                .message("Lấy danh sách tất cả các tiêu chí đánh giá thành công")
                .data(evaluationCriteriaRepository.findAll())
                .build();
    }

    @Override
    public ApiResponse<EvaluationCriteria> findById(Integer id) {
        if (!evaluationCriteriaRepository.existsById(id)) {
            throw new NotFoundException("Không tồn tại dữ liệu về tiêu chí đánh giá này");
        }
        EvaluationCriteria evaluationCriteria = evaluationCriteriaRepository.findById(id).get();
        return ApiResponse.<EvaluationCriteria>builder()
                .success(true)
                .timestamp(LocalDateTime.now())
                .message("Lấy dữ liệu về tiêu chí đánh giá thành công")
                .data(evaluationCriteria)
                .build();
    }

    @Override
    public ApiResponse<EvaluationCriteria> createEvaluationCriteria(AddEvaluationRequest request) {
        List<FieldErrorResponse> errorResponses = new ArrayList<>();

        Long count = evaluationCriteriaRepository.countByCriterionName(request.getCriterionName());
        if (count > 0) {
            errorResponses.add(new FieldErrorResponse("criterionName", "Tiêu chí đánh giá này đã tồn tại"));
        }
        if (!errorResponses.isEmpty()) {
            throw new ConflictDataException(errorResponses);
        }
        EvaluationCriteria evaluationCriteria = EvaluationMapper.toEntity(request);
        return ApiResponse.<EvaluationCriteria>builder()
                .success(true)
                .timestamp(LocalDateTime.now())
                .message("Thêm mới 1 tiêu chí đánh giá thành công")
                .data(evaluationCriteriaRepository.save(evaluationCriteria))
                .build();
    }

    @Override
    public ApiResponse<EvaluationCriteria> updateEvaluationCriteria(Integer id, UpdateEvaluationRequest request) {
        if (!evaluationCriteriaRepository.existsById(id)) {
            throw new NotFoundException("Không tìm thấy tài nguyên");
        }
        List<FieldErrorResponse> errorResponses = new ArrayList<>();
        Long count = evaluationCriteriaRepository.countByCriterionNameExcludeId(request.getCriterionName(), id);
        if (count > 0) {
            errorResponses.add(new FieldErrorResponse("criterionName", "Tên đã được sử dụng"));
        }
        if (!errorResponses.isEmpty()) {
            throw new ConflictDataException(errorResponses);
        }
        EvaluationCriteria evaluationCriteriaExisted = evaluationCriteriaRepository.findById(id).get();
        EvaluationCriteria evaluationCriteria = EvaluationMapper.toEntity(evaluationCriteriaExisted, request);
        return ApiResponse.<EvaluationCriteria>builder()
                .success(true)
                .timestamp(LocalDateTime.now())
                .data(evaluationCriteriaRepository.save(evaluationCriteria))
                .message("Cập nhật thông tin tiêu chí đánh giá thành công")
                .build();
    }

    @Override
    public ApiResponse<Void> deleteEvaluationCriteria(Integer id) {
        if (!evaluationCriteriaRepository.existsById(id)) {
            throw new NotFoundException("Không tìm thấy tài nguyên");
        }
        evaluationCriteriaRepository.deleteById(id);
        return ApiResponse.<Void>builder()
                .success(true)
                .timestamp(LocalDateTime.now())
                .message("Đã xóa thành công tiêu chí đánh giá có id = "+id)
                .build();
    }
}
