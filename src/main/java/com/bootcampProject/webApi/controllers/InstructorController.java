package com.bootcampProject.webApi.controllers;

import com.bootcampProject.business.abstracts.InstructorService;
import com.bootcampProject.business.requests.create.instructor.CreateInstructorRequest;
import com.bootcampProject.business.responses.create.CreateInstructorResponse;
import com.bootcampProject.business.responses.get.GetAllInstructorResponse;
import com.bootcampProject.business.responses.get.GetInstructorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/instructors")
public class InstructorController {
    private final InstructorService instructorService;

    @Autowired
    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @PostMapping("/add")
    public CreateInstructorResponse addInstructor(@RequestBody CreateInstructorRequest request) {
        return instructorService.add(request);
    }

    @GetMapping("/get/{id}")
    public GetInstructorResponse getInstructorById(@PathVariable int id) {
        return instructorService.getById(id);
    }

    @GetMapping("/get/all")
    public List<GetAllInstructorResponse> getAllInstructors() {
        return instructorService.getAll();
    }

    @PutMapping("/update")
    public void updateInstructor(@RequestBody CreateInstructorRequest request) {
        instructorService.update(request);
    }

    @DeleteMapping("/delete")
    public void deleteInstructor(@PathVariable int id) {
        instructorService.delete(id);
    }
}
