package com.bootcampProject.business.concretes;

import com.bootcampProject.business.abstracts.BootcampService;
import com.bootcampProject.business.constants.BootcampMessages;
import com.bootcampProject.business.requests.create.bootcamp.CreateBootcampRequest;
import com.bootcampProject.business.responses.create.bootcamp.CreateBootcampResponse;
import com.bootcampProject.business.responses.get.bootcamp.GetAllBootcampResponse;
import com.bootcampProject.business.responses.get.bootcamp.GetBootcampResponse;
import com.bootcampProject.core.utilities.mapping.ModelMapperService;
import com.bootcampProject.core.utilities.paging.PageDto;
import com.bootcampProject.core.utilities.results.DataResult;
import com.bootcampProject.core.utilities.results.Result;
import com.bootcampProject.core.utilities.results.SuccessDataResult;
import com.bootcampProject.core.utilities.results.SuccessResult;
import com.bootcampProject.dataAccess.abstracts.BootcampRepository;
import com.bootcampProject.entities.concretes.Bootcamp;
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
public class BootcampManager implements BootcampService {
    private final BootcampRepository bootcampRepository;
    private final ModelMapperService mapperService;

    @Autowired
    public BootcampManager(BootcampRepository bootcampRepository, ModelMapperService mapperService) {
        this.bootcampRepository = bootcampRepository;
        this.mapperService = mapperService;
    }

    @Override
    public DataResult<CreateBootcampResponse> add(CreateBootcampRequest request) {
        Bootcamp bootcamp = mapperService.forRequest().map(request, Bootcamp.class);
        bootcamp.setCreatedDate(LocalDateTime.now());
        bootcampRepository.save(bootcamp);

        CreateBootcampResponse response = mapperService.forResponse()
                .map(bootcamp, CreateBootcampResponse.class);
        return new SuccessDataResult<>(response, BootcampMessages.bootcampAdded);
    }

    @Override
    public Result delete(int id) {
        bootcampRepository.deleteById(id);
        return new SuccessResult(BootcampMessages.bootcampDeleted);
    }

    @Override
    public Result update(CreateBootcampRequest request) {
        int bootcampId = request.getId();
        Bootcamp existingBootcamp = bootcampRepository.findById(bootcampId).orElse(null);

        if (existingBootcamp == null) {
            // id not found
            return new SuccessResult(BootcampMessages.bootcampNotFound);
        }
        mapperService.forRequest().map(request, existingBootcamp);
        bootcampRepository.save(existingBootcamp);
        return new SuccessResult(BootcampMessages.bootcampUpdated);
    }

    @Override
    public DataResult<List<GetAllBootcampResponse>> getAll() {
        List<Bootcamp> bootcamps = bootcampRepository.findAll();
        List<GetAllBootcampResponse> bootcampResponses = bootcamps.stream()
                .map(bootcamp -> mapperService.forResponse().map(bootcamp, GetAllBootcampResponse.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(bootcampResponses, BootcampMessages.bootcampsListed);
    }

    @Override
    public DataResult<GetBootcampResponse> getById(int id) {
        Bootcamp bootcamp = bootcampRepository.getById(id);
        GetBootcampResponse response = mapperService.forResponse().map(bootcamp, GetBootcampResponse.class);
        return new SuccessDataResult<>(response, BootcampMessages.bootcampListed);
    }

    @Override
    public DataResult<List<GetAllBootcampResponse>> getAllPage(PageDto pageDto) {
        Sort sort = Sort.by(Sort.Direction.fromString(pageDto.getSortDirection()), pageDto.getSortBy());
        Pageable pageable = PageRequest.of(pageDto.getPageNumber(), pageDto.getPageSize(), sort);
        Page<Bootcamp> bootcamps = bootcampRepository.findAll(pageable);
        List<GetAllBootcampResponse> bootcampPages = bootcamps.stream()
                .map(bootcamp -> mapperService.forResponse().map(bootcamp, GetAllBootcampResponse.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(bootcampPages, BootcampMessages.bootcampsListed);
    }
}
