package com.bootcampProject.business.concretes;

import com.bootcampProject.business.abstracts.InstructorService;
import com.bootcampProject.business.requests.create.instructor.CreateInstructorRequest;
import com.bootcampProject.business.responses.create.CreateInstructorResponse;
import com.bootcampProject.business.responses.get.GetAllInstructorResponse;
import com.bootcampProject.business.responses.get.GetInstructorResponse;
import com.bootcampProject.core.utilities.mapping.ModelMapperService;
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
    public CreateInstructorResponse add(CreateInstructorRequest request) {
        Instructor instructor = mapperService.forRequest().map(request, Instructor.class);
        instructor.setCreatedDate(LocalDateTime.now());
        instructorRepository.save(instructor);

        CreateInstructorResponse response = mapperService.forResponse()
                .map(instructor, CreateInstructorResponse.class);
        return response;
    }

    @Override
    public void delete(int id) {
        instructorRepository.deleteById(id);

    }

    @Override
    public void update(CreateInstructorRequest request) {
    }

    @Override
    public List<GetAllInstructorResponse> getAll() {
        List<Instructor> instructors = instructorRepository.findAll();
        return instructors.stream()
                .map(instructor -> mapperService.forResponse().map(instructor, GetAllInstructorResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public GetInstructorResponse getById(int id) {
        Instructor instructor = instructorRepository.getById(id);
        GetInstructorResponse response = mapperService.forResponse().map(instructor,GetInstructorResponse.class);
        return response;
    }
}
