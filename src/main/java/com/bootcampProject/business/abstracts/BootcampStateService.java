package com.bootcampProject.business.abstracts;

import com.bootcampProject.business.requests.create.bootcampState.CreateBootcampStateRequest;
import com.bootcampProject.business.responses.create.bootcampState.CreateBootcampStateResponse;
import com.bootcampProject.business.responses.get.bootcampState.GetAllBootcampStateResponse;
import com.bootcampProject.business.responses.get.bootcampState.GetBootcampStateResponse;
import com.bootcampProject.core.utilities.results.DataResult;

import java.util.List;

public interface BootcampStateService {
    DataResult<CreateBootcampStateResponse> add(CreateBootcampStateRequest request);
    DataResult<Void> delete(int id);
    DataResult<Void> update(CreateBootcampStateRequest request);
    DataResult<List<GetAllBootcampStateResponse>> getAll();
    DataResult<GetBootcampStateResponse> getById(int id);
}
