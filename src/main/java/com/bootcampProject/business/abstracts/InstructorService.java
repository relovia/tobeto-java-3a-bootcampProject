package com.bootcampProject.business.abstracts;

import com.bootcampProject.business.requests.create.instructor.CreateInstructorRequest;
import com.bootcampProject.business.responses.create.instructors.CreateInstructorResponse;
import com.bootcampProject.business.responses.get.instructors.GetAllInstructorResponse;
import com.bootcampProject.business.responses.get.instructors.GetInstructorResponse;
import java.util.List;

public interface InstructorService {
    CreateInstructorResponse add(CreateInstructorRequest request);
    void delete(int id);
    void update(CreateInstructorRequest request);
    List<GetAllInstructorResponse> getAll();
    GetInstructorResponse getById(int id);
}
