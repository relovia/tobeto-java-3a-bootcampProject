package com.bootcampProject.business.responses.get.employees;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetEmployeeResponse {
    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private String employeePosition;
    private LocalDateTime createdDate;
}
