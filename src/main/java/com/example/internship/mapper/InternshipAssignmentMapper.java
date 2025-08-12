package com.example.internship.mapper;

import com.example.internship.dto.request.internshipassignment.AddInternshipAssignment;
import com.example.internship.dto.request.internshipassignment.UpdateInternshipAssignment;
import com.example.internship.entity.InternshipAssignment;
import com.example.internship.entity.StatusAssignment;

import javax.swing.text.DateFormatter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InternshipAssignmentMapper {

    private static StatusAssignment convertToStatusAssignment(String status) {
        StatusAssignment statusAssignment;
        switch (status) {
            case "pending":
                statusAssignment = StatusAssignment.PENDING;
                break;
            case "in progress":
                statusAssignment = StatusAssignment.IN_PROGRESS;
                break;
            case "completed":
                statusAssignment = StatusAssignment.COMPLETED;
                break;
            case "cancelled":
                statusAssignment = StatusAssignment.CANCELLED;
                break;
            default:
                statusAssignment = StatusAssignment.PENDING;

        }
        return statusAssignment;
    }

    public static InternshipAssignment toEntity(AddInternshipAssignment request) {
        InternshipAssignment internshipAssignment = new InternshipAssignment();
        internshipAssignment.setStatus(convertToStatusAssignment(request.getStatus()));

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        internshipAssignment.setAssignedDate(LocalDateTime.parse(request.getAssignedDate(), dtf));

        return internshipAssignment;
    }

    public  static InternshipAssignment toEntity(InternshipAssignment internshipAssignmentExisted,UpdateInternshipAssignment request) {
        internshipAssignmentExisted.setStatus(convertToStatusAssignment(request.getStatus()));
        return internshipAssignmentExisted;
    }
}
