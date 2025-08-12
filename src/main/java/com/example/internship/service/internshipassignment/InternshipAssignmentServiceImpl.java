package com.example.internship.service.internshipassignment;


import com.example.internship.config.exception.AccessDeniedException;
import com.example.internship.config.exception.ConflictDataException;
import com.example.internship.config.exception.NotFoundException;
import com.example.internship.dto.request.internshipassignment.AddInternshipAssignment;
import com.example.internship.dto.request.internshipassignment.UpdateInternshipAssignment;
import com.example.internship.dto.response.ApiResponse;
import com.example.internship.dto.response.FieldErrorResponse;
import com.example.internship.entity.InternshipAssignment;
import com.example.internship.entity.InternshipPhase;
import com.example.internship.entity.Mentor;
import com.example.internship.entity.Student;
import com.example.internship.mapper.InternshipAssignmentMapper;
import com.example.internship.repository.InternshipAssignmentRepository;
import com.example.internship.repository.InternshipPhaseRepository;
import com.example.internship.repository.MentorRepository;
import com.example.internship.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InternshipAssignmentServiceImpl implements IInternshipAssignmentService{
    private final InternshipAssignmentRepository internshipAssignmentRepository;
    private final MentorRepository mentorRepository;
    private final StudentRepository studentRepository;
    private final InternshipPhaseRepository internshipPhaseRepository;

    @Override
    public ApiResponse<List<InternshipAssignment>> findAllByAdmin() {
        return ApiResponse.<List<InternshipAssignment>>builder()
                .success(true)
                .data(internshipAssignmentRepository.findAll())
                .message("Lấy danh sách các phân công thực tập thành công")
                .timestamp(LocalDateTime.now())
                .build();
    }

    @Override
    public ApiResponse<List<InternshipAssignment>> findAllByMentor( Integer idLogin) {
        Mentor mentor = mentorRepository.findById(idLogin).get();
        List<InternshipAssignment> list = internshipAssignmentRepository.findAllByMentor(mentor);
        return ApiResponse.<List<InternshipAssignment>>builder()
                .success(true)
                .data(list)
                .timestamp(LocalDateTime.now())
                .message("Lấy danh sách các giai đoạn thực tập được giảng viên "+mentor.getUser().getFullName()+" hướng dẫn thành công")
                .build();
    }

    @Override
    public ApiResponse<List<InternshipAssignment>> findAllByStudent( Integer idLogin) {
        Student student = studentRepository.findById(idLogin).get();
        List<InternshipAssignment> list = internshipAssignmentRepository.findAllByStudent(student);
        return ApiResponse.<List<InternshipAssignment>>builder()
                .success(true)
                .data(list)
                .timestamp(LocalDateTime.now())
                .message("Lấy danh sách các giai đoạn thực tập của sinh viên "+student.getUser().getFullName()+" thành công")
                .build();
    }

    @Override
    public ApiResponse<InternshipAssignment> findById(Integer id) {
        InternshipAssignment internshipAssignment = internshipAssignmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tài nguyên ko tồn tại"));
        return ApiResponse.<InternshipAssignment>builder()
                .success(true)
                .data(internshipAssignment)
                .timestamp(LocalDateTime.now())
                .message("Lấy chi tiết phân công thực tập thành công")
                .build();
    }

    @Override
    public ApiResponse<InternshipAssignment> findByIdByMentor(Integer id,Integer idLogin) {
        if (!Objects.equals(id, idLogin)) {
            throw new AccessDeniedException("Không có quyền truy cập tài nguyên này");
        }
        InternshipAssignment internshipAssignment = internshipAssignmentRepository.findByIdByMentor(id, idLogin)
                .orElseThrow(() -> new NotFoundException("Tài nguyên không tồn tại"));
        return ApiResponse.<InternshipAssignment>builder()
                .success(true)
                .data(internshipAssignment)
                .timestamp(LocalDateTime.now())
                .message("Lấy chi tiết phân công thực tập thành công")
                .build();
    }

    @Override
    public ApiResponse<InternshipAssignment> findByIdByStudent(Integer id, Integer idLogin) {
        InternshipAssignment internshipAssignment = internshipAssignmentRepository.findByIdByStudent(id, idLogin)
                .orElseThrow(() -> new AccessDeniedException("Không có quyền truy cập"));


        return ApiResponse.<InternshipAssignment>builder()
                .success(true)
                .data(internshipAssignment)
                .timestamp(LocalDateTime.now())
                .message("Lấy chi tiết phân công thực tập thành công")
                .build();
    }

    @Override
    public ApiResponse<InternshipAssignment> create(AddInternshipAssignment request) {
        Optional<Student> student =  studentRepository.findById(request.getStudentId());
        Optional<InternshipPhase> internshipPhase = internshipPhaseRepository.findById(request.getPhaseId());
        Optional<Mentor>  mentor = mentorRepository.findById(request.getMentorId());

        if(student.isEmpty() || internshipPhase.isEmpty() || mentor.isEmpty()) {
            throw new NotFoundException("Tài nguyên không tồn tại");
        }
        List<FieldErrorResponse> errorResponses = new ArrayList<>();
        Long count = internshipAssignmentRepository.countByStudentAndInternshipPhase(student.get(), internshipPhase.get());
        if(count > 0) {
            errorResponses.add(new FieldErrorResponse("UNIQUE", "Cặp giá trị giữa internshipPhase và student này đã tồn tại"));
        }
        if (!errorResponses.isEmpty()) {
            throw new ConflictDataException(errorResponses);
        }
        InternshipAssignment internshipAssignment = InternshipAssignmentMapper.toEntity(request);
        internshipAssignment.setMentor(mentor.get());
        internshipAssignment.setStudent(student.get());
        internshipAssignment.setInternshipPhase(internshipPhase.get());
        return ApiResponse.<InternshipAssignment>builder()
                .success(true)
                .message("Thêm mới 1 dữ liệu phân công thực tập thành công")
                .timestamp(LocalDateTime.now())
                .data(internshipAssignmentRepository.save(internshipAssignment))
                .build();
    }

    @Override
    public ApiResponse<InternshipAssignment> update(Integer id, UpdateInternshipAssignment request) {
        if(!internshipAssignmentRepository.existsById(id)) {
            throw new NotFoundException("Tài nguyên không tồn tại");
        }
        InternshipAssignment internshipAssignmentExisted = internshipAssignmentRepository.findById(id).get();
        InternshipAssignment internshipAssignment = InternshipAssignmentMapper.toEntity(internshipAssignmentExisted, request);
        return ApiResponse.<InternshipAssignment>builder()
                .success(true)
                .data(internshipAssignmentRepository.save(internshipAssignment))
                .timestamp(LocalDateTime.now())
                .message("Cập nhật thành công")
                .build();
    }
}
