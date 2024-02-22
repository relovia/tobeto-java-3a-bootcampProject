package com.bootcampProject.business.concretes;

import com.bootcampProject.business.abstracts.BootcampStateService;
import com.bootcampProject.business.constants.BootcampStateMessages;
import com.bootcampProject.business.requests.create.bootcampState.CreateBootcampStateRequest;
import com.bootcampProject.business.responses.create.bootcampState.CreateBootcampStateResponse;
import com.bootcampProject.business.responses.get.bootcampState.GetAllBootcampStateResponse;
import com.bootcampProject.business.responses.get.bootcampState.GetBootcampStateResponse;
import com.bootcampProject.core.utilities.mapping.ModelMapperService;
import com.bootcampProject.core.utilities.results.DataResult;
import com.bootcampProject.core.utilities.results.SuccessDataResult;
import com.bootcampProject.dataAccess.abstracts.BootcampStateRepository;
import com.bootcampProject.entities.concretes.BootcampState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BootcampStateManager implements BootcampStateService {
    private final BootcampStateRepository bootcampStateRepository;
    private final ModelMapperService mapperService;

    @Autowired
    public BootcampStateManager(BootcampStateRepository bootcampStateRepository, ModelMapperService mapperService) {
        this.bootcampStateRepository = bootcampStateRepository;
        this.mapperService = mapperService;
    }

    @Override
    public DataResult<CreateBootcampStateResponse> add(CreateBootcampStateRequest request) {
        BootcampState bootcampState = mapperService.forRequest().map(request, BootcampState.class);
        bootcampState.setCreatedDate(LocalDateTime.now());
        bootcampStateRepository.save(bootcampState);

        CreateBootcampStateResponse response = mapperService.forResponse()
                .map(bootcampState, CreateBootcampStateResponse.class);
        return new SuccessDataResult<>(response, BootcampStateMessages.bootcampStateAdded);
    }

    @Override
    public DataResult<Void> delete(int id) {
        bootcampStateRepository.deleteById(id);
        return new SuccessDataResult<>(null, BootcampStateMessages.bootcampStateDeleted);
    }

    @Override
    public DataResult<Void> update(CreateBootcampStateRequest request) {
        int bootcampStateId = request.getId();
        BootcampState existingBootcampState = bootcampStateRepository.findById(bootcampStateId).orElse(null);

        if (existingBootcampState == null) {
            // id not found
            return new SuccessDataResult<>(null, BootcampStateMessages.bootcampStateNotFound);
        }
        mapperService.forResponse().map(request, existingBootcampState);
        bootcampStateRepository.save(existingBootcampState);
        return new SuccessDataResult<>(null, BootcampStateMessages.bootcampStateUpdated);
    }

    @Override
    public DataResult<List<GetAllBootcampStateResponse>> getAll() {
        List<BootcampState> bootcampStates = bootcampStateRepository.findAll();
        List<GetAllBootcampStateResponse> bootcampStateResponses = bootcampStates.stream()
                .map(bootcampState -> mapperService.forResponse().map(bootcampState, GetAllBootcampStateResponse.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(bootcampStateResponses, BootcampStateMessages.bootcampStatesListed);
    }

    @Override
    public DataResult<GetBootcampStateResponse> getById(int id) {
        BootcampState bootcampState = bootcampStateRepository.getById(id);
        GetBootcampStateResponse response = mapperService.forResponse().map(bootcampState, GetBootcampStateResponse.class);
        return new SuccessDataResult<>(response, BootcampStateMessages.bootcampStateListed);
    }
}
