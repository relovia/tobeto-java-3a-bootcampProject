package com.bootcampProject.business.concretes;

import com.bootcampProject.business.abstracts.EmployeeService;
import com.bootcampProject.business.constants.EmployeeMessages;
import com.bootcampProject.business.requests.create.employee.CreateEmployeeRequest;
import com.bootcampProject.business.responses.create.employees.CreateEmployeeResponse;
import com.bootcampProject.business.responses.get.employees.GetAllEmployeeResponse;
import com.bootcampProject.business.responses.get.employees.GetEmployeeResponse;
import com.bootcampProject.core.utilities.mapping.ModelMapperService;
import com.bootcampProject.core.utilities.paging.PageDto;
import com.bootcampProject.core.utilities.results.DataResult;
import com.bootcampProject.core.utilities.results.Result;
import com.bootcampProject.core.utilities.results.SuccessDataResult;
import com.bootcampProject.core.utilities.results.SuccessResult;
import com.bootcampProject.dataAccess.abstracts.EmployeeRepository;
import com.bootcampProject.entities.concretes.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeManager implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapperService mapperService;

    @Autowired
    public EmployeeManager(EmployeeRepository employeeRepository, ModelMapperService mapperService) {
        this.employeeRepository = employeeRepository;
        this.mapperService = mapperService;
    }

    @Override
    public DataResult<CreateEmployeeResponse> add(CreateEmployeeRequest request) {
        Employee employee = mapperService.forRequest().map(request, Employee.class);
        employee.setCreatedDate(LocalDateTime.now());
        employeeRepository.save(employee);

        CreateEmployeeResponse response = mapperService.forResponse()
                .map(employee, CreateEmployeeResponse.class);
        // Java 7 ve sonrası için <>'ın içini doldurmaya gerek yoktur.
        return new SuccessDataResult<>(response, EmployeeMessages.employeeAdded);
    }

    @Override
    public Result delete(int id) {
        employeeRepository.deleteById(id);
        return new SuccessResult(EmployeeMessages.employeeDeleted);
    }

    @Override
    public Result update(CreateEmployeeRequest request) {
        int employeeId = request.getId();
        Employee existingEmployee = employeeRepository.findById(employeeId).orElse(null);

        if (existingEmployee == null) {
            // id not found
            return new SuccessDataResult<>(null, EmployeeMessages.employeeNotFound);
        }
        mapperService.forRequest().map(request, existingEmployee);
        employeeRepository.save(existingEmployee);
        return new SuccessResult(EmployeeMessages.employeeUpdated);
    }
    @Override
    public DataResult<List<GetAllEmployeeResponse>> getAll() {
        List<Employee> employees = employeeRepository.findAll();
        List<GetAllEmployeeResponse> employeeResponses = employees.stream()
                .map(employee -> mapperService.forResponse().map(employee, GetAllEmployeeResponse.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(employeeResponses, EmployeeMessages.employeesListed);
    }

    @Override
    public DataResult<GetEmployeeResponse> getById(int id) {
        Employee employee = employeeRepository.getById(id);
        GetEmployeeResponse response = mapperService.forResponse().map(employee, GetEmployeeResponse.class);
        return new SuccessDataResult<>(response, EmployeeMessages.employeeListed);
    }

    @Override
    public DataResult<List<GetAllEmployeeResponse>> getAllPage(PageDto pageDto) {
        Sort sort = Sort.by(Sort.Direction.fromString(pageDto.getSortDirection()), pageDto.getSortBy());
        Pageable pageable = PageRequest.of(pageDto.getPageNumber(), pageDto.getPageSize(), sort);
        Page<Employee> employees = employeeRepository.findAll(pageable);
        List<GetAllEmployeeResponse> employeePages = employees.stream()
                .map(employee -> mapperService.forResponse().map(employees, GetAllEmployeeResponse.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(employeePages, EmployeeMessages.employeesListed);
    }
}
