package com.bootcampProject.business.abstracts;

import com.bootcampProject.business.requests.create.application.CreateApplicationRequest;
import com.bootcampProject.business.responses.create.application.CreateApplicationResponse;
import com.bootcampProject.business.responses.get.application.GetAllApplicationResponse;
import com.bootcampProject.business.responses.get.application.GetApplicationResponse;
import com.bootcampProject.core.utilities.paging.PageDto;
import com.bootcampProject.core.utilities.results.DataResult;
import com.bootcampProject.core.utilities.results.Result;

import java.util.List;

public interface ApplicationService {
    DataResult<CreateApplicationResponse> add(CreateApplicationRequest request);
    Result delete(int id);
    Result update(CreateApplicationRequest request);
    DataResult<List<GetAllApplicationResponse>> getAll();
    DataResult<GetApplicationResponse> getById(int id);
    DataResult<List<GetAllApplicationResponse>> getAllPage(PageDto pageDto);

}
