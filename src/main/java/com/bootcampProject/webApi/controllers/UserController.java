package com.bootcampProject.webApi.controllers;

import com.bootcampProject.business.abstracts.UserService;
import com.bootcampProject.business.requests.create.user.CreateUserRequest;
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

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody CreateUserRequest request) {
        return handleDataResult(userService.add(request));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getUserById(@PathVariable  int id) {
        return handleDataResult(userService.getById(id));
    }

    @GetMapping("/get/all")
    public ResponseEntity<?> getAllUsers() {
        return handleDataResult(userService.getAll());
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody CreateUserRequest request) {
        return handleDataResult(userService.update(request));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        return handleDataResult(userService.delete(id));
    }
}
