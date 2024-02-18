package com.bootcampProject.business.abstracts;

import com.bootcampProject.business.requests.create.applicant.CreateApplicantRequest;
import com.bootcampProject.business.responses.create.CreateApplicantResponse;
import com.bootcampProject.business.responses.get.GetAllApplicantResponse;
import com.bootcampProject.business.responses.get.GetApplicantResponse;
import java.util.List;

public interface ApplicantService {
    CreateApplicantResponse add(CreateApplicantRequest request);
    void delete(int id);
    void update(CreateApplicantRequest request);
    List<GetAllApplicantResponse> getAll();
    GetApplicantResponse getById(int id);
}
