package com.example.internship.service.assessmentresutl;

import com.example.internship.config.exception.AccessDeniedException;
import com.example.internship.config.exception.ConflictDataException;
import com.example.internship.dto.request.assessmentresult.AddAssessmentResult;
import com.example.internship.dto.request.assessmentresult.UpdateAssessmentResult;
import com.example.internship.dto.response.ApiResponse;
import com.example.internship.dto.response.FieldErrorResponse;
import com.example.internship.entity.*;
import com.example.internship.mapper.AssessmentResultMapper;
import com.example.internship.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AssessmentResultServiceImpl implements IAssessmentResultService {
    private final AssessmentResultRepository assessmentResultRepository;
    private final InternshipAssignmentRepository internshipAssignmentRepository;
    private final MentorRepository mentorRepository;
    private final StudentRepository studentRepository;
    private final AssessmentRoundRepository  assessmentRoundRepository;
    private final EvaluationCriteriaRepository evaluationCriteriaRepository;

    @Override
    public ApiResponse<List<AssessmentResults>> findAll() {
        return ApiResponse.<List<AssessmentResults>>builder()
                .success(true)
                .message("Lấy danh sách kết quả thành công")
                .data(assessmentResultRepository.findAll())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @Override
    public ApiResponse<List<AssessmentResults>> findAllByMentor(Integer idLogin) {
        Mentor mentor = mentorRepository.findById(idLogin).get();
//        List<InternshipAssignment> assignmentList = internshipAssignmentRepository.findAllByMentor(mentor);
//        List<AssessmentResults> resultList = assignmentList.stream()
//                .flatMap(assignment -> assessmentResultRepository
//                        .findByInternshipAssignment_Id(assignment.getId())
//                        .stream()
//                )
//                .toList();
        List<AssessmentResults> list =  assessmentResultRepository.findByMentorId(idLogin);

        return ApiResponse.<List<AssessmentResults>>builder()
                .success(true)
                .data(list)
                .message("Lấy danh sách các kết quả của các đợt thực tập được giảng viên "+mentor.getUser().getFullName()+" thành công")
                .build();
    }

    @Override
    public ApiResponse<List<AssessmentResults>> findAllByStudent(Integer idLogin) {
        Student student = studentRepository.findById(idLogin).get();
        List<AssessmentResults> list = assessmentResultRepository.findByStudentId(idLogin);

        return ApiResponse.<List<AssessmentResults>>builder()
                .success(true)
                .data(list)
                .message("Lấy danh sách các kết quả của các đợt thực tập của sinh viên "+student.getUser().getFullName()+ " thành công")
                .build();
    }

    @Override
    public ApiResponse<AssessmentResults> create(AddAssessmentResult request, Integer idLogin) {

        InternshipAssignment internshipAssignment = internshipAssignmentRepository.findById(request.getInternshipAssignmentId())
                .orElseThrow(()-> new EntityNotFoundException("InternshipAssignment not found"));
        AssessmentRound assessmentRound = assessmentRoundRepository.findById(request.getAssessmentRoundId())
                .orElseThrow(()-> new EntityNotFoundException("AssessmentRound not found"));
        EvaluationCriteria evaluationCriteria = evaluationCriteriaRepository.findById(request.getEvaluationCriteriaId())
                .orElseThrow(()-> new EntityNotFoundException("EvaluationCriteria not found"));

        Mentor mentor = mentorRepository.findById(idLogin).get();
        // ktra coi mentor đang login có đang thêm vào đúng assignment đc phân công ko
        if (!Objects.equals(idLogin, internshipAssignment.getMentor().getMentorId())) {
            throw new AccessDeniedException("Mentor ko có quyền truy cập");
        }

        List<FieldErrorResponse> errorResponses = new ArrayList<>();
        Long count = assessmentResultRepository.countByUnique(
                internshipAssignment,
                evaluationCriteria,
                assessmentRound
        );

        if (count > 0) {
            errorResponses.add(new FieldErrorResponse("UNIQUE", "Kết quả đánh giá đã tồn tại"));
        }

        if (!errorResponses.isEmpty()) {
            throw new ConflictDataException(errorResponses);
        }

        AssessmentResults assessmentResults = AssessmentResultMapper.toEntity(request);
        assessmentResults.setInternshipAssignment(internshipAssignment);
        assessmentResults.setAssessmentRound(assessmentRound);
        assessmentResults.setEvaluationCriteria(evaluationCriteria);
        assessmentResults.setMentor(mentor);
        return ApiResponse.<AssessmentResults>builder()
                .data(assessmentResultRepository.save(assessmentResults))
                .timestamp(LocalDateTime.now())
                .message("Thêm thành công")
                .success(true)
                .build();
    }

    @Override
    public ApiResponse<AssessmentResults> update(UpdateAssessmentResult request,Integer assessmentResultId, Integer idLogin) {
        AssessmentResults assessmentResultsExisted = assessmentResultRepository.findById(assessmentResultId)
                .orElseThrow(()-> new EntityNotFoundException("AssessmentResults not found"));

        // ktra coi mentor đang login có đang thêm vào đúng assignment đc phân công ko
        Mentor mentor = mentorRepository.findById(idLogin).get();
        if (!Objects.equals(idLogin, assessmentResultsExisted.getInternshipAssignment().getMentor().getMentorId())) {
            throw new AccessDeniedException("Mentor ko có quyền truy cập");
        }
        AssessmentResults assessmentResults = AssessmentResultMapper.toEntity(assessmentResultsExisted,request);
        return ApiResponse.<AssessmentResults>builder()
                .data(assessmentResultRepository.save(assessmentResults))
                .timestamp(LocalDateTime.now())
                .message("Cập nhật thành công")
                .success(true)
                .build();
    }
}
