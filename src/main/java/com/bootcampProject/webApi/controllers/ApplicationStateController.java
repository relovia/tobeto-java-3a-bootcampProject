package com.bootcampProject.webApi.controllers;

import com.bootcampProject.business.abstracts.ApplicationStateService;
import com.bootcampProject.business.requests.create.applicationState.CreateApplicationStateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/applicationState")
public class ApplicationStateController extends BaseController {
    private final ApplicationStateService applicationStateService;

    @Autowired
    public ApplicationStateController(ApplicationStateService applicationStateService) {
        this.applicationStateService = applicationStateService;
    }
    @PostMapping("/add")
    public ResponseEntity<?> addApplication(@RequestBody CreateApplicationStateRequest request) {
        return handleDataResult(applicationStateService.add(request));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getApplicationId(@PathVariable int id) {
        return handleDataResult(applicationStateService.getById(id));
    }
    @GetMapping("/get/all")
    public ResponseEntity<?> getAllApplications() {
        return handleDataResult(applicationStateService.getAll());
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateApplication(@RequestBody CreateApplicationStateRequest request) {
        return handleDataResult(applicationStateService.update(request));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteApplication(@PathVariable int id) {
        return handleDataResult(applicationStateService.delete(id));
    }
}
