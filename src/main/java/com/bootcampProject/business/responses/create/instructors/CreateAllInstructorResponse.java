package com.bootcampProject.business.responses.create.instructors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAllInstructorResponse {
    private int id;
    private String companyName;
    private LocalDateTime createdDate;
}
