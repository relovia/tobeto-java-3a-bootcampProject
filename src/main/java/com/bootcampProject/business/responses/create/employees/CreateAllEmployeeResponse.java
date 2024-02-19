package com.bootcampProject.business.responses.create.employees;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAllEmployeeResponse {
    private int id;
    private String position;
}
