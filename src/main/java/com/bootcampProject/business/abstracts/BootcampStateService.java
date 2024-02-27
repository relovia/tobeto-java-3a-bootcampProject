package com.bootcampProject.business.abstracts;

import com.bootcampProject.business.requests.create.bootcampState.CreateBootcampStateRequest;
import com.bootcampProject.business.responses.create.bootcampState.CreateBootcampStateResponse;
import com.bootcampProject.business.responses.get.bootcampState.GetAllBootcampStateResponse;
import com.bootcampProject.business.responses.get.bootcampState.GetBootcampStateResponse;
import com.bootcampProject.core.utilities.paging.PageDto;
import com.bootcampProject.core.utilities.results.DataResult;
import com.bootcampProject.core.utilities.results.Result;

import java.util.List;

public interface BootcampStateService {
    DataResult<CreateBootcampStateResponse> add(CreateBootcampStateRequest request);
    Result delete(int id);
    Result update(CreateBootcampStateRequest request);
    DataResult<List<GetAllBootcampStateResponse>> getAll();
    DataResult<GetBootcampStateResponse> getById(int id);
    DataResult<List<GetAllBootcampStateResponse>> getAllPage(PageDto pageDto);

}
