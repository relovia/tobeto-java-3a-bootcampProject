package com.bootcampProject.business.responses.create.instructors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAllInstructorResponse {
    private int id;
    private String companyName;
}