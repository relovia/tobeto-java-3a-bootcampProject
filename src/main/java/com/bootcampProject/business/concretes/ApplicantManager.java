package com.bootcampProject.business.concretes;

import com.bootcampProject.business.abstracts.ApplicantService;
import com.bootcampProject.business.abstracts.BaseService;
import com.bootcampProject.business.abstracts.BlacklistService;
import com.bootcampProject.business.constants.ApplicantMessages;
import com.bootcampProject.business.requests.create.applicant.CreateApplicantRequest;
import com.bootcampProject.business.requests.update.application.UpdateApplicationRequest;
import com.bootcampProject.business.responses.create.applicants.CreateApplicantResponse;
import com.bootcampProject.business.responses.get.applicants.GetAllApplicantResponse;
import com.bootcampProject.business.responses.get.applicants.GetApplicantResponse;
import com.bootcampProject.core.aspects.logging.Loggable;
import com.bootcampProject.core.exceptions.types.BusinessException;
import com.bootcampProject.core.utilities.mapping.ModelMapperService;
import com.bootcampProject.core.utilities.paging.PageDto;
import com.bootcampProject.core.utilities.results.DataResult;
import com.bootcampProject.core.utilities.results.Result;
import com.bootcampProject.core.utilities.results.SuccessDataResult;
import com.bootcampProject.core.utilities.results.SuccessResult;
import com.bootcampProject.dataAccess.abstracts.ApplicantRepository;
import com.bootcampProject.entities.concretes.Applicant;
import com.bootcampProject.entities.concretes.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicantManager implements ApplicantService, BaseService {
    private final ApplicantRepository applicantRepository;
    private final ModelMapperService mapperService;
    private final BlacklistService blacklistService;

    @Autowired
    public ApplicantManager(ApplicantRepository applicantRepository, ModelMapperService mapperService, BlacklistService blacklistService) {
        this.applicantRepository = applicantRepository;
        this.mapperService = mapperService;
        this.blacklistService = blacklistService;
    }

    @Override
    @Loggable
    public DataResult<CreateApplicantResponse> add(CreateApplicantRequest request) {
        checkIfUserExists(request.getEmail());
        checkIfUsernameExists(request.getUsername());

        if (blacklistService.isBlacklisted(request.getEmail())) {
            throw new BusinessException("Applicant is blacklisted and cannot apply...");
        }

        Applicant applicant = mapperService.forRequest().map(request, Applicant.class);
        applicant.setCreatedDate(LocalDateTime.now());
        applicantRepository.save(applicant);

        CreateApplicantResponse response = mapperService.forResponse()
                .map(applicant, CreateApplicantResponse.class);
        return new SuccessDataResult<>(response, ApplicantMessages.applicantAdded);
    }

    @Override
    public Result delete(int id) {
        applicantRepository.deleteById(id);
        return new SuccessResult(ApplicantMessages.applicantDeleted);
    }

    @Override
    @Loggable
    public Result update(UpdateApplicationRequest request) {
        int applicantId = request.getId();
        Applicant existingApplicant = applicantRepository.findById(applicantId).orElse(null);

        if (existingApplicant == null) {
            // id not found
            return new SuccessResult(ApplicantMessages.applicantNotFound);
        }
        mapperService.forRequest().map(request, existingApplicant);
        applicantRepository.save(existingApplicant);
        return new SuccessResult(ApplicantMessages.applicantUpdated);
    }

    @Override
    @Loggable
    public DataResult<List<GetAllApplicantResponse>> getAll() {
        List<Applicant> applicants = applicantRepository.findAll();
        List<GetAllApplicantResponse> applicantResponses = applicants.stream()
                .map(applicant -> mapperService.forResponse().map(applicant, GetAllApplicantResponse.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(applicantResponses, ApplicantMessages.applicantsListed);
    }

    @Override
    public DataResult<GetApplicantResponse> getById(int id) {
        Applicant applicant = applicantRepository.getById(id);
        GetApplicantResponse response = mapperService.forResponse().map(applicant, GetApplicantResponse.class);
        return new SuccessDataResult<>(response, ApplicantMessages.applicantListed);
    }

    @Override
    public DataResult<List<GetAllApplicantResponse>> getAllPage(PageDto pageDto) {
        Sort sort = Sort.by(Sort.Direction.fromString(pageDto.getSortDirection()), pageDto.getSortBy());
        Pageable pageable = PageRequest.of(pageDto.getPageNumber(), pageDto.getPageSize(), sort);
        Page<Applicant> applicants = applicantRepository.findAll(pageable);
        List<GetAllApplicantResponse> applicantsPage = applicants.stream()
                .map(applicant -> mapperService.forResponse().map(applicant, GetAllApplicantResponse.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(applicantsPage);
    }

    @Override
    public void checkIfUserExists(String email) {
        User applicant = applicantRepository.getByEmail(email.trim());
        if (applicant != null) {
            throw new BusinessException("Applicant email already exists!");
        }
    }

    @Override
    public void checkIfUsernameExists(String username) {
        User applicant = applicantRepository.getByUsername(username.trim());
        if (applicant != null) {
            throw new BusinessException("Applicant username already exists");
        }
    }
}