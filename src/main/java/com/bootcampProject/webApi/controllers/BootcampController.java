package com.bootcampProject.webApi.controllers;

import com.bootcampProject.business.abstracts.BootcampService;
import com.bootcampProject.business.requests.create.bootcamp.CreateBootcampRequest;
import com.bootcampProject.core.utilities.paging.PageDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bootcamps")
public class BootcampController extends BaseController {
    private final BootcampService bootcampService;

    @Autowired
    public BootcampController(BootcampService bootcampService) {
        this.bootcampService = bootcampService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addBootcamp(@RequestBody @Valid CreateBootcampRequest request) {
        return handleDataResult(bootcampService.add(request));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getBootcampId(@PathVariable int id) {
        return handleDataResult(bootcampService.getById(id));
    }

    @GetMapping("/get/all")
    public ResponseEntity<?> getAllBootcamps() {
        return handleDataResult(bootcampService.getAll());
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateBootcamp(@RequestBody CreateBootcampRequest request) {
        return handleResult(bootcampService.update(request));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBootcamp(@PathVariable int id) {
        return handleResult(bootcampService.delete(id));
    }
    @GetMapping("/sort")
    public ResponseEntity<?> getAllPage(@RequestBody PageDto pageDto) {
        return handleResult(bootcampService.getAllPage(pageDto));
    }
}
