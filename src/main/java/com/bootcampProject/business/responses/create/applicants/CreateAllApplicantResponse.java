package com.bootcampProject.business.responses.create.applicants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAllApplicantResponse {
    private int id;
    private String about;
    private LocalDateTime createdDate;
}
