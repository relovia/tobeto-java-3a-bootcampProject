package com.bootcampProject.webApi.controllers;

import com.bootcampProject.business.abstracts.UserService;
import com.bootcampProject.core.utilities.paging.PageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController extends BaseController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get/all")
    public ResponseEntity<?> getAllUsers() {
        return handleDataResult(userService.getAll());
    }

    @GetMapping("/get/user/email/{email}")
    public ResponseEntity<?> getUserEmail(@PathVariable String email) {
        return handleDataResult(userService.getByEmail(email));
    }

    @GetMapping("/sort")
    public ResponseEntity<?> getAllPage(@RequestBody PageDto pageDto) {
        return handleResult(userService.getAllPage(pageDto));
    }
}
