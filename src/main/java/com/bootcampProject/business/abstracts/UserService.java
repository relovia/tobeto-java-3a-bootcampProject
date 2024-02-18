package com.bootcampProject.business.abstracts;

import com.bootcampProject.business.requests.create.user.CreateUserRequest;
import com.bootcampProject.business.responses.create.CreateUserResponse;
import com.bootcampProject.business.responses.get.GetAllUserResponse;
import com.bootcampProject.business.responses.get.GetUserResponse;
import java.util.List;

public interface UserService {
    CreateUserResponse add(CreateUserRequest request);
    void delete(int id);
    void update(CreateUserRequest request);
    List<GetAllUserResponse> getAll();
    GetUserResponse getById(int id);
    GetUserResponse getByEmail(String email);
}
