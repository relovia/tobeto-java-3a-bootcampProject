package com.bootcampProject.webApi.controllers;

import com.bootcampProject.business.abstracts.EmployeeService;
import com.bootcampProject.business.requests.create.employee.CreateEmployeeRequest;
import com.bootcampProject.core.utilities.paging.PageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController extends BaseController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addEmployee(@RequestBody CreateEmployeeRequest request) {
        return handleDataResult(employeeService.add(request));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable int id) {
        return handleDataResult(employeeService.getById(id));
    }

    @GetMapping("/get/all")
    public ResponseEntity<?> getAllEmployees() {
        return handleDataResult(employeeService.getAll());
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateEmployee(@RequestBody CreateEmployeeRequest request) {
       return handleResult(employeeService.update(request));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable int id) {
        return handleResult(employeeService.delete(id));
    }
    @GetMapping("/sort")
    public ResponseEntity<?> getAllPage(@RequestBody PageDto pageDto) {
        return handleResult(employeeService.getAllPage(pageDto));
    }
}
