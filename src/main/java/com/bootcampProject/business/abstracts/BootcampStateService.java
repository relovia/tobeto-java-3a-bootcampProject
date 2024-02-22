package com.bootcampProject.business.abstracts;

import com.bootcampProject.business.requests.create.bootcamp.CreateBootcampRequest;
import com.bootcampProject.business.responses.create.bootcamp.CreateBootcampResponse;
import com.bootcampProject.business.responses.create.bootcampState.CreateBootcampStateResponse;
import com.bootcampProject.business.responses.get.bootcamp.GetAllBootcampResponse;
import com.bootcampProject.business.responses.get.bootcamp.GetBootcampResponse;
import com.bootcampProject.business.responses.get.bootcampState.GetAllBootcampStateResponse;
import com.bootcampProject.business.responses.get.bootcampState.GetBootcampStateResponse;
import com.bootcampProject.core.utilities.results.DataResult;

import java.util.List;

public interface BootcampStateService {
    DataResult<CreateBootcampStateResponse> add(CreateBootcampStateResponse request);
    DataResult<Void> delete(int id);
    DataResult<Void> update(CreateBootcampStateResponse request);
    DataResult<List<GetAllBootcampStateResponse>> getAll();
    DataResult<GetBootcampStateResponse> getById(int id);
}
