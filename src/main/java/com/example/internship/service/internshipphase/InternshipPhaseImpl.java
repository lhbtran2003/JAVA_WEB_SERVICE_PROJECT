package com.example.internship.service.internshipphase;

import com.example.internship.config.exception.BadRequestException;
import com.example.internship.config.exception.ConflictDataException;
import com.example.internship.config.exception.NotFoundException;
import com.example.internship.dto.request.internshipphase.AddPhaseRequest;
import com.example.internship.dto.request.internshipphase.UpdatePhaseRequest;
import com.example.internship.dto.response.ApiResponse;
import com.example.internship.entity.InternshipPhase;
import com.example.internship.mapper.PhaseMapper;
import com.example.internship.repository.AssessmentRoundRepository;
import com.example.internship.repository.InternshipPhaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InternshipPhaseImpl implements IInternshipPhaseService{

    private final InternshipPhaseRepository internshipPhaseRepository;
    private final AssessmentRoundRepository assessmentRoundRepository;

    @Override
    public ApiResponse<List<InternshipPhase>> findAllInternshipPhases() {
        return ApiResponse.<List<InternshipPhase>>builder()
                .success(true)
                .message("Lấy danh sách các giai đoạn thực tập thành công")
                .data(internshipPhaseRepository.findAll())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @Override
    public ApiResponse<InternshipPhase> findById(Integer internshipPhaseId) {
        InternshipPhase internshipPhase = internshipPhaseRepository.findById(internshipPhaseId).orElseThrow(() -> new NotFoundException("Internship Phase Not Found"));
        return ApiResponse.<InternshipPhase>builder()
                .success(true)
                .message("Lấy thông tin giai đoạn thực tập thành công")
                .data(internshipPhase)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @Override
    public ApiResponse<InternshipPhase> createInternshipPhase(AddPhaseRequest request) {
        Long count = internshipPhaseRepository.countInternshipPhaseByPhaseName(request.getPhaseName());
        if (count > 0) {
            throw  new ConflictDataException("Tên đã được sử dụng");
        }
        InternshipPhase internshipPhase = PhaseMapper.toEntity(request);
        return ApiResponse.<InternshipPhase>builder()
                .success(true)
                .timestamp(LocalDateTime.now())
                .message("Tạo thành công giai đoạn thực tập mới")
                .data(internshipPhaseRepository.save(internshipPhase))
                .build();
    }

    @Override
    public ApiResponse<InternshipPhase> updateInternshipPhase(Integer internshipPhaseId, UpdatePhaseRequest request) {
        if (!internshipPhaseRepository.existsById(internshipPhaseId)) {
            throw  new NotFoundException("Internship Phase Not Found");
        }
        Long count = internshipPhaseRepository.countPhaseExcludeId(request.getPhaseName(), internshipPhaseId);
        if (count > 0) {
            throw  new ConflictDataException("Tên đã được sử dụng");

        }
        InternshipPhase internshipPhaseExisted = internshipPhaseRepository.findById(internshipPhaseId).get();
        InternshipPhase internshipPhase= PhaseMapper.toEntity(internshipPhaseExisted,request);
        return ApiResponse.<InternshipPhase>builder()
                .success(true)
                .message("Cập nhật thông tin của giai đoạn thực tập thành công")
                .data(internshipPhaseRepository.save(internshipPhase))
                .timestamp(LocalDateTime.now())
                .build();
    }

    @Override
    public ApiResponse<Void> deleteInternshipPhase(Integer internshipPhaseId) {
        if (!internshipPhaseRepository.existsById(internshipPhaseId)) {
            throw  new NotFoundException("Internship Phase Not Found");
        }
        InternshipPhase internshipPhaseExisted = internshipPhaseRepository.findById(internshipPhaseId).get();
        if (assessmentRoundRepository.existsByInternshipPhase(internshipPhaseExisted)) {
            throw new BadRequestException("Không thể xóa dữ liệu giai đoạn thực tập này vì đang chứa các đợt đánh giá");
        }
        internshipPhaseRepository.deleteById(internshipPhaseId);
        return ApiResponse.<Void>builder()
                .success(true)
                .message("Đã xóa thành công dữ liệu về giai đoạn thực tập này")
                .timestamp(LocalDateTime.now())
                .build();
    }
}
