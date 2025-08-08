package com.example.internship.dto.request.mentor;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateMentorRequest {
    @Size(min = 1, max = 100)
    private String department;
    @Size(min = 1, max = 100)
    private String academicRank;
}
