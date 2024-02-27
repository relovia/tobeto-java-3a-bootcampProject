package com.bootcampProject.business.responses.get.instructors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllInstructorResponse {
    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private String instructorCompanyName;
    private LocalDateTime createdDate;
}
