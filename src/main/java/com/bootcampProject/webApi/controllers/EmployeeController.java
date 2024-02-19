package com.bootcampProject.webApi.controllers;

import com.bootcampProject.business.abstracts.EmployeeService;
import com.bootcampProject.business.requests.create.employee.CreateEmployeeRequest;
import com.bootcampProject.business.responses.create.employees.CreateEmployeeResponse;
import com.bootcampProject.business.responses.get.employees.GetAllEmployeeResponse;
import com.bootcampProject.business.responses.get.employees.GetEmployeeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/add")
    public CreateEmployeeResponse addEmployee(@RequestBody CreateEmployeeRequest request) {
        return employeeService.add(request);
    }

    @GetMapping("/get/{id}")
    public GetEmployeeResponse getEmployeeById(@PathVariable int id) {
        return employeeService.getById(id);
    }

    @GetMapping("/get/all")
    public List<GetAllEmployeeResponse> getAllEmployees() {
        return employeeService.getAll();
    }

    @PutMapping("/update")
    public void updateEmployee(@RequestBody CreateEmployeeRequest request) {
        employeeService.update(request);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEmployee(@PathVariable int id) {
        employeeService.delete(id);
    }
}
