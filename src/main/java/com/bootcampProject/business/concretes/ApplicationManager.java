package com.bootcampProject.business.concretes;

import com.bootcampProject.business.abstracts.ApplicationService;
import com.bootcampProject.business.constants.ApplicationMessages;
import com.bootcampProject.business.requests.create.application.CreateApplicationRequest;
import com.bootcampProject.business.responses.create.application.CreateApplicationResponse;
import com.bootcampProject.business.responses.get.application.GetAllApplicationResponse;
import com.bootcampProject.business.responses.get.application.GetApplicationResponse;
import com.bootcampProject.core.utilities.mapping.ModelMapperService;
import com.bootcampProject.core.utilities.paging.PageDto;
import com.bootcampProject.core.utilities.results.DataResult;
import com.bootcampProject.core.utilities.results.Result;
import com.bootcampProject.core.utilities.results.SuccessDataResult;
import com.bootcampProject.core.utilities.results.SuccessResult;
import com.bootcampProject.dataAccess.abstracts.ApplicationRepository;
import com.bootcampProject.entities.concretes.Application;
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
public class ApplicationManager implements ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final ModelMapperService mapperService;

    @Autowired
    public ApplicationManager(ApplicationRepository applicationRepository, ModelMapperService mapperService) {
        this.applicationRepository = applicationRepository;
        this.mapperService = mapperService;
    }

    @Override
    public DataResult<CreateApplicationResponse> add(CreateApplicationRequest request) {
        Application application = mapperService.forRequest().map(request, Application.class);
        application.setCreatedDate(LocalDateTime.now());
        applicationRepository.save(application);

        CreateApplicationResponse response = mapperService.forResponse()
                .map(application, CreateApplicationResponse.class);
        return new SuccessDataResult<>(response, ApplicationMessages.applicationAdded);
    }

    @Override
    public Result delete(int id) {
        applicationRepository.deleteById(id);
        return new SuccessResult(ApplicationMessages.applicationDeleted);
    }

    @Override
    public Result update(CreateApplicationRequest request) {
        int applicationId = request.getId();
        Application existingApplication = applicationRepository.findById(applicationId).orElse(null);

        if (existingApplication == null) {
            // id not found
            return new SuccessResult(ApplicationMessages.applicationNotFound);
        }
        mapperService.forRequest().map(request, existingApplication);
        applicationRepository.save(existingApplication);
        return new SuccessResult(ApplicationMessages.applicationUpdated);
    }

    @Override
    public DataResult<List<GetAllApplicationResponse>> getAll() {
        List<Application> applications = applicationRepository.findAll();
        List<GetAllApplicationResponse> applicationResponses = applications.stream()
                .map(application -> mapperService.forResponse().map(application, GetAllApplicationResponse.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(applicationResponses, ApplicationMessages.applicationsListed);
    }

    @Override
    public DataResult<GetApplicationResponse> getById(int id) {
        Application application = applicationRepository.getById(id);
        GetApplicationResponse response = mapperService.forResponse().map(application, GetApplicationResponse.class);
        return new SuccessDataResult<>(response, ApplicationMessages.applicationListed);
    }

    @Override
    public DataResult<List<GetAllApplicationResponse>> getAllPage(PageDto pageDto) {
        Sort sort = Sort.by(Sort.Direction.fromString(pageDto.getSortDirection()), pageDto.getSortBy());
        Pageable pageable = PageRequest.of(pageDto.getPageNumber(), pageDto.getPageSize(), sort);
        Page<Application> applications = applicationRepository.findAll(pageable);
        List<GetAllApplicationResponse> applicationsPage = applications.stream()
                .map(application -> mapperService.forResponse().map(application, GetAllApplicationResponse.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(applicationsPage, ApplicationMessages.applicationsListed);
    }

}
