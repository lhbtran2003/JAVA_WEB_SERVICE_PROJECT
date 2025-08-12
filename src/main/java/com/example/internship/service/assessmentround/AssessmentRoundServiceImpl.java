package com.example.internship.service.assessmentround;

import com.example.internship.config.exception.NotFoundException;
import com.example.internship.dto.request.assessmentround.AddAssessmentRound;
import com.example.internship.dto.request.assessmentround.UpdateAssessmentRound;
import com.example.internship.dto.response.ApiResponse;
import com.example.internship.entity.AssessmentRound;
import com.example.internship.entity.InternshipPhase;
import com.example.internship.mapper.AssessmentRoundMapper;
import com.example.internship.repository.AssessmentRoundRepository;
import com.example.internship.repository.InternshipPhaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AssessmentRoundServiceImpl implements IAssessmentRoundService {

    private final AssessmentRoundRepository assessmentRoundRepository;
    private final InternshipPhaseRepository internshipPhaseRepository;

    @Override
    public ApiResponse<List<AssessmentRound>> findAll() {
        return ApiResponse.<List<AssessmentRound>>builder()
                .success(true)
                .message("Lấy danh sách các đợt đánh giá thành công")
                .data(assessmentRoundRepository.findAll())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @Override
    public ApiResponse<AssessmentRound> findById(Integer id) {
        AssessmentRound assessmentRound = assessmentRoundRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tài nguyên không tồn tại"));
        return ApiResponse.<AssessmentRound>builder()
                .success(true)
                .message("Lấy thông tin về đợt đánh giá thành công")
                .data(assessmentRound)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @Override
    public ApiResponse<List<AssessmentRound>> findByInternshipPhase(String phaseName) {
        InternshipPhase internshipPhase = internshipPhaseRepository.findByPhaseNameContainingIgnoreCase(phaseName)
                .orElseThrow(() -> new NotFoundException("Tài nguyên về giai đoạn thực tập không tồn tại"));

        List<AssessmentRound> list = assessmentRoundRepository.findByInternshipPhase(internshipPhase);

        if (list.isEmpty()) {
            return ApiResponse.<List<AssessmentRound>>builder()
                    .success(true)
                    .message("Chưa có đợt đánh giá nào")
                    .timestamp(LocalDateTime.now())
                    .build();
        }
        return ApiResponse.<List<AssessmentRound>>builder()
                .success(true)
                .message("Lấy danh sách các đợt đánh giá của " + phaseName + " thành công")
                .data(list)
                .timestamp(LocalDateTime.now())
                .build();
    }


    @Override
    public ApiResponse<AssessmentRound> create(AddAssessmentRound request) {
        InternshipPhase internshipPhase = internshipPhaseRepository.findById(request.getInternshipPhaseId())
                .orElseThrow(() -> new NotFoundException("Giai đoạn thực tập không tồn tại"));
        AssessmentRound assessmentRound = AssessmentRoundMapper.toEntity(request);
        assessmentRound.setInternshipPhase(internshipPhase);
        return ApiResponse.<AssessmentRound>builder()
                .success(true)
                .timestamp(LocalDateTime.now())
                .message("Thêm mới 1 giai đoạn thành công")
                .data(assessmentRoundRepository.save(assessmentRound))
                .build();
    }

    @Override
    public ApiResponse<AssessmentRound> update(Integer id, UpdateAssessmentRound request) {
        if (!assessmentRoundRepository.existsById(id) || !internshipPhaseRepository.existsById(request.getInternshipPhaseId())) {
            throw new NotFoundException("Tài nguyên ko tồn tại");
        }
        AssessmentRound assessmentRoundExisted = assessmentRoundRepository.findById(id).get();
        InternshipPhase internshipPhaseExisted = internshipPhaseRepository.findById(request.getInternshipPhaseId()).get();
        AssessmentRound assessmentRound = AssessmentRoundMapper.toEntity(assessmentRoundExisted, request);
        assessmentRound.setInternshipPhase(internshipPhaseExisted);
        return ApiResponse.<AssessmentRound>builder()
                .success(true)
                .timestamp(LocalDateTime.now())
                .message("Cập nhật giai đoạn thành công")
                .data(assessmentRoundRepository.save(assessmentRound))
                .build();
    }

    @Override
    public ApiResponse<Void> delete(Integer id) {
        if (!assessmentRoundRepository.existsById(id)) {
            throw new NotFoundException("Tài nguyên ko tồn tại");
        }
        assessmentRoundRepository.deleteById(id);
        return ApiResponse.<Void>builder()
                .success(true)
                .timestamp(LocalDateTime.now())
                .message("Xóa giai đoạn thành công")
                .build();
    }
}
