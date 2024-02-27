package com.bootcampProject.business.abstracts;

import com.bootcampProject.business.requests.create.bootcamp.CreateBootcampRequest;
import com.bootcampProject.business.responses.create.bootcamp.CreateBootcampResponse;
import com.bootcampProject.business.responses.get.bootcamp.GetAllBootcampResponse;
import com.bootcampProject.business.responses.get.bootcamp.GetBootcampResponse;
import com.bootcampProject.core.utilities.paging.PageDto;
import com.bootcampProject.core.utilities.results.DataResult;
import com.bootcampProject.core.utilities.results.Result;

import java.util.List;

public interface BootcampService {
    DataResult<CreateBootcampResponse> add(CreateBootcampRequest request);
    Result delete(int id);
    Result update(CreateBootcampRequest request);
    DataResult<List<GetAllBootcampResponse>> getAll();
    DataResult<GetBootcampResponse> getById(int id);
    DataResult<List<GetAllBootcampResponse>> getAllPage(PageDto pageDto);

}
