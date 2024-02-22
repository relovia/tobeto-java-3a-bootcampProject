package com.bootcampProject.business.abstracts;

import com.bootcampProject.business.requests.create.user.CreateUserRequest;
import com.bootcampProject.business.responses.create.users.CreateUserResponse;
import com.bootcampProject.business.responses.get.users.GetAllUserResponse;
import com.bootcampProject.business.responses.get.users.GetUserResponse;
import com.bootcampProject.core.utilities.results.DataResult;

import java.util.List;

public interface UserService {
    DataResult<CreateUserResponse> add(CreateUserRequest request);
    DataResult<Void> delete(int id);
    DataResult<Void> update(CreateUserRequest request);
    DataResult<List<GetAllUserResponse>> getAll();
    DataResult<GetUserResponse> getById(int id);
    DataResult<GetUserResponse> getByEmail(String email);
}
