package com.bootcampProject.business.abstracts;

import com.bootcampProject.business.requests.create.applicationState.CreateApplicationStateRequest;
import com.bootcampProject.business.responses.create.applicationState.CreateApplicationStateResponse;
import com.bootcampProject.business.responses.get.applicationState.GetAllApplicationStateResponse;
import com.bootcampProject.business.responses.get.applicationState.GetApplicationStateResponse;
import com.bootcampProject.core.utilities.results.DataResult;

import java.util.List;

public interface ApplicationStateService {
    DataResult<CreateApplicationStateResponse> add(CreateApplicationStateRequest request);
    DataResult<Void> delete(int id);
    DataResult<Void> update(CreateApplicationStateRequest request);
    DataResult<List<GetAllApplicationStateResponse>> getAll();
    DataResult<GetApplicationStateResponse> getById(int id);
}
