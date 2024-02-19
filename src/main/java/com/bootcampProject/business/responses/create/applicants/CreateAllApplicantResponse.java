package com.bootcampProject.business.responses.create.applicants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAllApplicantResponse {
    private int id;
    private String about;
}
