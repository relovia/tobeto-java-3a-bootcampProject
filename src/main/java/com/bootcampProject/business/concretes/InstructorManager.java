package com.bootcampProject.business.concretes;

import com.bootcampProject.business.abstracts.InstructorService;
import com.bootcampProject.business.constants.InstructorMessages;
import com.bootcampProject.business.requests.create.instructor.CreateInstructorRequest;
import com.bootcampProject.business.responses.create.instructors.CreateInstructorResponse;
import com.bootcampProject.business.responses.get.instructors.GetAllInstructorResponse;
import com.bootcampProject.business.responses.get.instructors.GetInstructorResponse;
import com.bootcampProject.core.utilities.mapping.ModelMapperService;
import com.bootcampProject.core.utilities.results.DataResult;
import com.bootcampProject.core.utilities.results.SuccessDataResult;
import com.bootcampProject.dataAccess.abstracts.InstructorRepository;
import com.bootcampProject.entities.concretes.Instructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstructorManager implements InstructorService {
    private final InstructorRepository instructorRepository;
    private final ModelMapperService mapperService;

    @Autowired
    public InstructorManager(InstructorRepository instructorRepository, ModelMapperService mapperService) {
        this.instructorRepository = instructorRepository;
        this.mapperService = mapperService;
    }

    @Override
    public DataResult<CreateInstructorResponse> add(CreateInstructorRequest request) {
        Instructor instructor = mapperService.forRequest().map(request, Instructor.class);
        instructor.setCreatedDate(LocalDateTime.now());
        instructorRepository.save(instructor);

        CreateInstructorResponse response = mapperService.forResponse()
                .map(instructor, CreateInstructorResponse.class);
        return new SuccessDataResult<>(response, InstructorMessages.instructorAdded);
    }

    @Override
    public DataResult<Void> delete(int id) {
        instructorRepository.deleteById(id);
        return new SuccessDataResult<>(null, InstructorMessages.instructorDeleted);
    }

    @Override
    public DataResult<Void> update(CreateInstructorRequest request) {
        int instructorId = request.getId();
        Instructor existingInstructor = instructorRepository.findById(instructorId).orElse(null);

        if (existingInstructor == null) {
            // id not found
            return new SuccessDataResult<>(null, InstructorMessages.instructorNotFound);
        }

        mapperService.forRequest().map(request, existingInstructor);
        instructorRepository.save(existingInstructor);

        return new SuccessDataResult<>(null, InstructorMessages.instructorUpdated);
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
}
