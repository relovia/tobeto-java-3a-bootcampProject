package com.bootcampProject.webApi.controllers;

import com.bootcampProject.business.abstracts.UserService;
import com.bootcampProject.business.requests.create.user.CreateUserRequest;
import com.bootcampProject.business.responses.create.CreateUserResponse;
import com.bootcampProject.business.responses.get.GetAllUserResponse;
import com.bootcampProject.business.responses.get.GetUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public CreateUserResponse addUser(@RequestBody CreateUserRequest request) {
        return userService.add(request);
    }

    @GetMapping("/get/{id}")
    public GetUserResponse getUserById(@PathVariable  int id) {
        return userService.getById(id);
    }

    @GetMapping("/get/all")
    public List<GetAllUserResponse> getAllUsers() {
        return userService.getAll();
    }

    @PutMapping("/update")
    public void updateUser(@RequestBody CreateUserRequest request) {
        userService.update(request);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.delete(id);
    }
}
