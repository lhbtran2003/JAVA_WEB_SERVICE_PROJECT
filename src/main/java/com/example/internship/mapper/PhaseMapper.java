package com.example.internship.mapper;

import com.example.internship.dto.request.internshipphase.AddPhaseRequest;
import com.example.internship.dto.request.internshipphase.UpdatePhaseRequest;
import com.example.internship.entity.InternshipPhase;

import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PhaseMapper {
    public static InternshipPhase toEntity(AddPhaseRequest request) {
        InternshipPhase internshipPhase = new InternshipPhase();
        internshipPhase.setPhaseName(request.getPhaseName());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        internshipPhase.setStartDate(LocalDate.parse(request.getStartDate(), formatter));
        internshipPhase.setEndDate(LocalDate.parse(request.getEndDate(), formatter));
        return internshipPhase;
    }

    public static InternshipPhase toEntity(InternshipPhase phase,UpdatePhaseRequest request) {
        phase.setPhaseName(request.getPhaseName());
        phase.setDescription(request.getDescription());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        phase.setStartDate(LocalDate.parse(request.getStartDate(), formatter));
        phase.setEndDate(LocalDate.parse(request.getEndDate(), formatter));
        return phase;
    }
}
