package com.bootcampProject.business.responses.get.applicants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetApplicantResponse {
    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private String about;
    private LocalDateTime createdDate;
}
