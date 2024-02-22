package com.bootcampProject.webApi.controllers;

import com.bootcampProject.business.abstracts.BootcampStateService;
import com.bootcampProject.business.requests.create.bootcampState.CreateBootcampStateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bootcampState")
public class BootcampStateController extends BaseController {
    private final BootcampStateService bootcampStateService;

    @Autowired
    public BootcampStateController(BootcampStateService bootcampStateService) {
        this.bootcampStateService = bootcampStateService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addBootcampState(@RequestBody CreateBootcampStateRequest request) {
        return handleDataResult(bootcampStateService.add(request));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getBootcampStateId(@PathVariable int id) {
        return handleDataResult(bootcampStateService.getById(id));
    }

    @GetMapping("/get/all")
    public ResponseEntity<?> getAllBootcampStates() {
        return handleDataResult(bootcampStateService.getAll());
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateBootcampState(@RequestBody CreateBootcampStateRequest request) {
        return handleDataResult(bootcampStateService.update(request));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBootcampState(@PathVariable int id) {
        return handleDataResult(bootcampStateService.delete(id));
    }
}
