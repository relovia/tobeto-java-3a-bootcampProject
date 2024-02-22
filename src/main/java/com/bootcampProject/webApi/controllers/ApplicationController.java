package com.bootcampProject.webApi.controllers;

import com.bootcampProject.business.abstracts.ApplicationService;
import com.bootcampProject.business.requests.create.application.CreateApplicationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController extends BaseController {
    private final ApplicationService applicationService;

    @Autowired
    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addApplication(@RequestBody CreateApplicationRequest request) {
        return handleDataResult(applicationService.add(request));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getApplicationId(@PathVariable int id) {
        return handleDataResult(applicationService.getById(id));
    }
    @GetMapping("/get/all")
    public ResponseEntity<?> getAllApplications() {
        return handleDataResult(applicationService.getAll());
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateApplications(@RequestBody CreateApplicationRequest request) {
        return handleDataResult(applicationService.update(request));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteApplications(@PathVariable int id) {
        return handleDataResult(applicationService.delete(id));
    }

}
