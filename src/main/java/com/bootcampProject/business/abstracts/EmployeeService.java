package com.bootcampProject.business.abstracts;

import com.bootcampProject.business.requests.create.employee.CreateEmployeeRequest;
import com.bootcampProject.business.responses.create.employees.CreateEmployeeResponse;
import com.bootcampProject.business.responses.get.employees.GetAllEmployeeResponse;
import com.bootcampProject.business.responses.get.employees.GetEmployeeResponse;
import com.bootcampProject.core.utilities.paging.PageDto;
import com.bootcampProject.core.utilities.results.DataResult;
import com.bootcampProject.core.utilities.results.Result;

import java.util.List;

public interface EmployeeService {
    DataResult<CreateEmployeeResponse> add(CreateEmployeeRequest request);
    Result delete(int id);
    Result update(CreateEmployeeRequest request);
    DataResult<List<GetAllEmployeeResponse>> getAll();
    DataResult<GetEmployeeResponse> getById(int id);
    DataResult<List<GetAllEmployeeResponse>> getAllPage(PageDto pageDto);

}
