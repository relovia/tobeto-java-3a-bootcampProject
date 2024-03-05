package com.bootcampProject.business.concretes;

import com.bootcampProject.business.abstracts.BaseService;
import com.bootcampProject.business.abstracts.EmployeeService;
import com.bootcampProject.business.constants.EmployeeMessages;
import com.bootcampProject.business.requests.create.employee.CreateEmployeeRequest;
import com.bootcampProject.business.requests.update.employee.UpdateEmployeeRequest;
import com.bootcampProject.business.responses.create.employees.CreateEmployeeResponse;
import com.bootcampProject.business.responses.get.employees.GetAllEmployeeResponse;
import com.bootcampProject.business.responses.get.employees.GetEmployeeResponse;
import com.bootcampProject.core.aspects.logging.Loggable;
import com.bootcampProject.core.exceptions.types.BusinessException;
import com.bootcampProject.core.utilities.mapping.ModelMapperService;
import com.bootcampProject.core.utilities.paging.PageDto;
import com.bootcampProject.core.utilities.results.DataResult;
import com.bootcampProject.core.utilities.results.Result;
import com.bootcampProject.core.utilities.results.SuccessDataResult;
import com.bootcampProject.core.utilities.results.SuccessResult;
import com.bootcampProject.dataAccess.abstracts.EmployeeRepository;
import com.bootcampProject.entities.concretes.Employee;
import com.bootcampProject.entities.concretes.User;
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
public class EmployeeManager implements EmployeeService, BaseService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapperService mapperService;

    @Autowired
    public EmployeeManager(EmployeeRepository employeeRepository, ModelMapperService mapperService) {
        this.employeeRepository = employeeRepository;
        this.mapperService = mapperService;
    }

    @Override
    @Loggable
    public DataResult<CreateEmployeeResponse> add(CreateEmployeeRequest request) {
        checkIfUserExists(request.getEmail());
        checkIfUsernameExists(request.getUsername());
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
    @Loggable
    public Result update(UpdateEmployeeRequest request) {
        int employeeId = request.getId();
        Employee existingEmployee = employeeRepository.findById(employeeId).orElse(null);

        if (existingEmployee == null) {
            // id not found
            return new SuccessResult(EmployeeMessages.employeeNotFound);
        }
        mapperService.forRequest().map(request, existingEmployee);
        employeeRepository.save(existingEmployee);
        return new SuccessResult(EmployeeMessages.employeeUpdated);
    }

    @Override
    @Loggable
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

    @Override
    public void checkIfUserExists(String email) {
        User employee = employeeRepository.getByEmail(email.trim());
        if (employee != null) {
            throw new BusinessException("Employee email already exists");
        }
    }

    @Override
    public void checkIfUsernameExists(String username) {
        User employee = employeeRepository.getByUsername(username.trim());
        if (employee != null) {
            throw new BusinessException("Employee username exists");
        }
    }
}
