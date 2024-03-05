package com.bootcampProject.business.abstracts;

import com.bootcampProject.business.responses.get.users.GetAllUserResponse;
import com.bootcampProject.business.responses.get.users.GetUserResponse;
import com.bootcampProject.core.utilities.paging.PageDto;
import com.bootcampProject.core.utilities.results.DataResult;

import java.util.List;

public interface UserService {
    DataResult<List<GetAllUserResponse>> getAll();

    DataResult<GetUserResponse> getByEmail(String email);

    DataResult<List<GetAllUserResponse>> getAllPage(PageDto pageDto);

}
