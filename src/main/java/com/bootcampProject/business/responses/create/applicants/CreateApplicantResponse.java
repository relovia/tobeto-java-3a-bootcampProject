package com.bootcampProject.business.responses.create.applicants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateApplicantResponse {
    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private String about;
    private LocalDateTime createdDate;
}
