package com.bootcampProject.business.concretes;

import com.bootcampProject.business.abstracts.BaseService;
import com.bootcampProject.business.abstracts.InstructorService;
import com.bootcampProject.business.constants.InstructorMessages;
import com.bootcampProject.business.requests.create.instructor.CreateInstructorRequest;
import com.bootcampProject.business.responses.create.instructors.CreateInstructorResponse;
import com.bootcampProject.business.responses.get.instructors.GetAllInstructorResponse;
import com.bootcampProject.business.responses.get.instructors.GetInstructorResponse;
import com.bootcampProject.core.exceptions.types.BusinessException;
import com.bootcampProject.core.utilities.mapping.ModelMapperService;
import com.bootcampProject.core.utilities.paging.PageDto;
import com.bootcampProject.core.utilities.results.DataResult;
import com.bootcampProject.core.utilities.results.Result;
import com.bootcampProject.core.utilities.results.SuccessDataResult;
import com.bootcampProject.core.utilities.results.SuccessResult;
import com.bootcampProject.dataAccess.abstracts.InstructorRepository;
import com.bootcampProject.entities.concretes.Instructor;
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
public class InstructorManager implements InstructorService, BaseService {
    private final InstructorRepository instructorRepository;
    private final ModelMapperService mapperService;

    @Autowired
    public InstructorManager(InstructorRepository instructorRepository, ModelMapperService mapperService) {
        this.instructorRepository = instructorRepository;
        this.mapperService = mapperService;
    }

    @Override
    public DataResult<CreateInstructorResponse> add(CreateInstructorRequest request) {
        checkIfUserExists(request.getEmail());
        checkIfUsernameExists(request.getUsername());
        Instructor instructor = mapperService.forRequest().map(request, Instructor.class);
        instructor.setCreatedDate(LocalDateTime.now());
        instructorRepository.save(instructor);

        CreateInstructorResponse response = mapperService.forResponse()
                .map(instructor, CreateInstructorResponse.class);
        return new SuccessDataResult<>(response, InstructorMessages.instructorAdded);
    }

    @Override
    public Result delete(int id) {
        instructorRepository.deleteById(id);
        return new SuccessResult(InstructorMessages.instructorDeleted);
    }

    @Override
    public Result update(CreateInstructorRequest request) {
        int instructorId = request.getId();
        Instructor existingInstructor = instructorRepository.findById(instructorId).orElse(null);

        if (existingInstructor == null) {
            // id not found
            return new SuccessResult(InstructorMessages.instructorNotFound);
        }

        mapperService.forRequest().map(request, existingInstructor);
        instructorRepository.save(existingInstructor);

        return new SuccessResult(InstructorMessages.instructorUpdated);
    }

    @Override
    public DataResult<List<GetAllInstructorResponse>> getAll() {
        List<Instructor> instructors = instructorRepository.findAll();
        List<GetAllInstructorResponse> instructorResponses = instructors.stream()
                .map(instructor -> mapperService.forResponse().map(instructor, GetAllInstructorResponse.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(instructorResponses, InstructorMessages.instructorsListed);
    }

    @Override
    public DataResult<GetInstructorResponse> getById(int id) {
        Instructor instructor = instructorRepository.getById(id);
        GetInstructorResponse response = mapperService.forResponse().map(instructor,GetInstructorResponse.class);
        return new SuccessDataResult<>(response, InstructorMessages.instructorListed);
    }

    @Override
    public DataResult<List<GetAllInstructorResponse>> getAllPage(PageDto pageDto) {
        Sort sort = Sort.by(Sort.Direction.fromString(pageDto.getSortDirection()), pageDto.getSortBy());
        Pageable pageable = PageRequest.of(pageDto.getPageNumber(), pageDto.getPageSize(), sort);
        Page<Instructor> instructors = instructorRepository.findAll(pageable);
        List<GetAllInstructorResponse> instructorPages = instructors.stream()
                .map(instructor -> mapperService.forResponse().map(instructors, GetAllInstructorResponse.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(instructorPages, InstructorMessages.instructorsListed);
    }

    @Override
    public void checkIfUserExists(String email) {
        User instructor = instructorRepository.getByEmail(email);
        if (instructor != null) {
            throw new BusinessException("Instructor email already exists");
        }
    }

    @Override
    public void checkIfUsernameExists(String username) {
        User instructor = instructorRepository.getByUsername(username);
        if (instructor != null) {
            throw new BusinessException("Instructor username already exists");
        }
    }
}
