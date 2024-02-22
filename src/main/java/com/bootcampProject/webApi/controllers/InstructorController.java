package com.bootcampProject.webApi.controllers;

import com.bootcampProject.business.abstracts.InstructorService;
import com.bootcampProject.business.requests.create.instructor.CreateInstructorRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/instructors")
public class InstructorController extends BaseController {
    private final InstructorService instructorService;

    @Autowired
    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addInstructor(@RequestBody CreateInstructorRequest request) {
        return handleDataResult(instructorService.add(request));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getInstructorById(@PathVariable int id) {
        return handleDataResult(instructorService.getById(id));
    }

    @GetMapping("/get/all")
    public ResponseEntity<?> getAllInstructors() {
        return handleDataResult(instructorService.getAll());
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateInstructor(@RequestBody CreateInstructorRequest request) {
        return handleDataResult(instructorService.update(request));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteInstructor(@PathVariable int id) {
        return handleDataResult(instructorService.delete(id));
    }
}
