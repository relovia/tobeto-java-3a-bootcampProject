package com.bootcampProject.business.abstracts;

import com.bootcampProject.business.requests.create.employee.CreateEmployeeRequest;
import com.bootcampProject.business.responses.create.CreateEmployeeResponse;
import com.bootcampProject.business.responses.get.GetAllEmployeeResponse;
import com.bootcampProject.business.responses.get.GetEmployeeResponse;
import java.util.List;

public interface EmployeeService {
    CreateEmployeeResponse add(CreateEmployeeRequest request);
    void delete(int id);
    void update(CreateEmployeeRequest request);
    List<GetAllEmployeeResponse> getAll();
    GetEmployeeResponse getById(int id);
}
