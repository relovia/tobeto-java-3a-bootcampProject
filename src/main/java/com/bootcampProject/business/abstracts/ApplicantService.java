package com.bootcampProject.business.abstracts;

import com.bootcampProject.business.requests.create.applicant.CreateApplicantRequest;
import com.bootcampProject.business.responses.create.applicants.CreateApplicantResponse;
import com.bootcampProject.business.responses.get.applicants.GetAllApplicantResponse;
import com.bootcampProject.business.responses.get.applicants.GetApplicantResponse;
import com.bootcampProject.core.utilities.paging.PageDto;
import com.bootcampProject.core.utilities.results.DataResult;
import com.bootcampProject.core.utilities.results.Result;

import java.util.List;

public interface ApplicantService {
    DataResult<CreateApplicantResponse> add(CreateApplicantRequest request);
    Result delete(int id);
    Result update(CreateApplicantRequest request);
    DataResult<List<GetAllApplicantResponse>> getAll();
    DataResult<GetApplicantResponse> getById(int id);
    DataResult<List<GetAllApplicantResponse>> getAllPage(PageDto pageDto);
}
