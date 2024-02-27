package com.bootcampProject.business.responses.create.employees;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAllEmployeeResponse {
    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private String position;
    private LocalDateTime createdDate;
}
