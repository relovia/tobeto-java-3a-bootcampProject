package com.bootcampProject.webApi.controllers;

import com.bootcampProject.business.abstracts.ApplicantService;
import com.bootcampProject.business.requests.create.applicant.CreateApplicantRequest;
import com.bootcampProject.business.responses.create.CreateAllApplicantResponse;
import com.bootcampProject.business.responses.create.CreateApplicantResponse;
import com.bootcampProject.business.responses.get.GetAllApplicantResponse;
import com.bootcampProject.business.responses.get.GetApplicantResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applicants")
public class ApplicantController {
    private final ApplicantService applicantService;

    @Autowired
    public ApplicantController(ApplicantService applicantService) {
        this.applicantService = applicantService;
    }

    @PostMapping("/add")
    public CreateApplicantResponse addApplicant(@RequestBody CreateApplicantRequest request) {
        return applicantService.add(request);
    }

    @GetMapping("/get/{id}")
    public GetApplicantResponse getApplicantById(@PathVariable int id) {
        return applicantService.getById(id);
    }

    @GetMapping("/get/all")
    public List<GetAllApplicantResponse> getAllApplicants() {
        return applicantService.getAll();
    }

    @PutMapping("/update")
    public void updateApplicant(@RequestBody CreateApplicantRequest request) {
        applicantService.update(request);
    }

    @DeleteMapping("delete/{id}")
    public void deleteApplicant(@PathVariable int id) {
        applicantService.delete(id);
    }
}
