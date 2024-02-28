package com.bootcampProject.webApi.controllers;

import com.bootcampProject.business.abstracts.BlacklistService;
import com.bootcampProject.business.requests.create.applicant.CreateApplicantRequest;
import com.bootcampProject.business.requests.create.blacklist.CreateBlacklistRequest;
import com.bootcampProject.core.utilities.paging.PageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/blacklists")
public class BlacklistController extends BaseController {
    private final BlacklistService blacklistService;

    @Autowired
    public BlacklistController(BlacklistService blacklistService) {
        this.blacklistService = blacklistService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addBlacklist(@RequestBody CreateBlacklistRequest request) {
        return handleDataResult(blacklistService.add(request));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getBlacklistById(@PathVariable int id) {
        return handleDataResult(blacklistService.getById(id));
    }

    @GetMapping("/get/all")
    public ResponseEntity<?> getAllBlacklists() {
        return handleDataResult(blacklistService.getAll());
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateBlacklist(@RequestBody CreateBlacklistRequest request) {
        return handleResult(blacklistService.update(request));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBlacklist(@PathVariable int id) {
        return handleResult(blacklistService.delete(id));
    }
    @GetMapping("/sort")
    public ResponseEntity<?> getAllPage(@RequestBody PageDto pageDto) {
        return handleResult(blacklistService.getAllPage(pageDto));
    }
}
