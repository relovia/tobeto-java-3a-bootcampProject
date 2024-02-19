package com.bootcampProject.business.concretes;

import com.bootcampProject.business.abstracts.ApplicantService;
import com.bootcampProject.business.requests.create.applicant.CreateApplicantRequest;
import com.bootcampProject.business.responses.create.applicants.CreateApplicantResponse;
import com.bootcampProject.business.responses.get.applicants.GetAllApplicantResponse;
import com.bootcampProject.business.responses.get.applicants.GetApplicantResponse;
import com.bootcampProject.core.utilities.mapping.ModelMapperService;
import com.bootcampProject.dataAccess.abstracts.ApplicantRepository;
import com.bootcampProject.entities.concretes.Applicant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicantManager implements ApplicantService {
    private final ApplicantRepository applicantRepository;
    private final ModelMapperService mapperService;

    @Autowired
    public ApplicantManager(ApplicantRepository applicantRepository, ModelMapperService mapperService) {
        this.applicantRepository = applicantRepository;
        this.mapperService = mapperService;
    }

    @Override
    public CreateApplicantResponse add(CreateApplicantRequest request) {
        Applicant applicant = mapperService.forRequest().map(request, Applicant.class);
        applicant.setCreatedDate(LocalDateTime.now());
        applicantRepository.save(applicant);

        CreateApplicantResponse response = mapperService.forResponse()
                .map(applicant, CreateApplicantResponse.class);
        return response;
    }

    @Override
    public void delete(int id) {
        applicantRepository.deleteById(id);
    }

    @Override
    public void update(CreateApplicantRequest request) {
    }

    @Override
    public List<GetAllApplicantResponse> getAll() {
        List<Applicant> applicants = applicantRepository.findAll();
        return applicants.stream()
                .map(applicant -> mapperService.forResponse().map(applicant, GetAllApplicantResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public GetApplicantResponse getById(int id) {
        Applicant applicant = applicantRepository.getById(id);
        GetApplicantResponse response = mapperService.forResponse().map(applicant, GetApplicantResponse.class);
        return response;
    }
}
