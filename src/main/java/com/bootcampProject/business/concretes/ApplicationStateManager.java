package com.bootcampProject.business.concretes;

import com.bootcampProject.business.abstracts.ApplicationStateService;
import com.bootcampProject.business.constants.ApplicationStateMessages;
import com.bootcampProject.business.requests.create.applicationState.CreateApplicationStateRequest;
import com.bootcampProject.business.responses.create.applicationState.CreateApplicationStateResponse;
import com.bootcampProject.business.responses.get.applicationState.GetAllApplicationStateResponse;
import com.bootcampProject.business.responses.get.applicationState.GetApplicationStateResponse;
import com.bootcampProject.core.utilities.mapping.ModelMapperService;
import com.bootcampProject.core.utilities.paging.PageDto;
import com.bootcampProject.core.utilities.results.DataResult;
import com.bootcampProject.core.utilities.results.Result;
import com.bootcampProject.core.utilities.results.SuccessDataResult;
import com.bootcampProject.core.utilities.results.SuccessResult;
import com.bootcampProject.dataAccess.abstracts.ApplicationStateRepository;
import com.bootcampProject.entities.concretes.ApplicationState;
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
public class ApplicationStateManager implements ApplicationStateService {
    private final ApplicationStateRepository applicationStateRepository;
    private final ModelMapperService mapperService;

    @Autowired
    public ApplicationStateManager(ApplicationStateRepository applicationStateRepository, ModelMapperService mapperService) {
        this.applicationStateRepository = applicationStateRepository;
        this.mapperService = mapperService;
    }

    @Override
    public DataResult<CreateApplicationStateResponse> add(CreateApplicationStateRequest request) {
        ApplicationState applicationState = mapperService.forRequest().map(request, ApplicationState.class);
        applicationState.setCreatedDate(LocalDateTime.now());
        applicationStateRepository.save(applicationState);

        CreateApplicationStateResponse response = mapperService.forResponse()
                .map(applicationState, CreateApplicationStateResponse.class);
        return new SuccessDataResult<>(response, ApplicationStateMessages.applicationStateAdded);
    }

    @Override
    public Result delete(int id) {
        applicationStateRepository.deleteById(id);
        return new SuccessResult(ApplicationStateMessages.applicationStateDeleted);
    }

    @Override
    public Result update(CreateApplicationStateRequest request) {
        int applicationStateId = request.getId();
        ApplicationState existingApplicationState = applicationStateRepository.findById(applicationStateId).orElse(null);

        if (existingApplicationState == null) {
            // id not found
            return new SuccessResult(ApplicationStateMessages.applicationStateNotFound);
        }
        mapperService.forRequest().map(request, existingApplicationState);
        applicationStateRepository.save(existingApplicationState);
        return new SuccessResult(ApplicationStateMessages.applicationStateUpdated);
    }

    @Override
    public DataResult<List<GetAllApplicationStateResponse>> getAll() {
        List<ApplicationState> applications = applicationStateRepository.findAll();
        List<GetAllApplicationStateResponse> applicationStateResponses = applications.stream()
                .map(application -> mapperService.forResponse().map(application, GetAllApplicationStateResponse.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(applicationStateResponses, ApplicationStateMessages.applicationStatesListed);
    }

    @Override
    public DataResult<GetApplicationStateResponse> getById(int id) {
        ApplicationState applicationState = applicationStateRepository.getById(id);
        GetApplicationStateResponse response = mapperService.forResponse().map(applicationState, GetApplicationStateResponse.class);
        return new SuccessDataResult<>(response, ApplicationStateMessages.applicationStateListed);
    }

    @Override
    public DataResult<List<GetAllApplicationStateResponse>> getAllPage(PageDto pageDto) {
        Sort sort = Sort.by(Sort.Direction.fromString(pageDto.getSortDirection()), pageDto.getSortBy());
        Pageable pageable = PageRequest.of(pageDto.getPageNumber(), pageDto.getPageSize(), sort);
        Page<ApplicationState> applicationStates = applicationStateRepository.findAll(pageable);
        List<GetAllApplicationStateResponse> applicationStatePages = applicationStates.stream()
                .map(applicationState -> mapperService.forResponse().map(applicationState, GetAllApplicationStateResponse.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(applicationStatePages, ApplicationStateMessages.applicationStatesListed);
    }
}
