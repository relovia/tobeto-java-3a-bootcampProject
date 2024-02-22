package com.bootcampProject.business.abstracts;

import com.bootcampProject.business.requests.create.instructor.CreateInstructorRequest;
import com.bootcampProject.business.responses.create.instructors.CreateInstructorResponse;
import com.bootcampProject.business.responses.get.instructors.GetAllInstructorResponse;
import com.bootcampProject.business.responses.get.instructors.GetInstructorResponse;
import com.bootcampProject.core.utilities.results.DataResult;

import java.util.List;

public interface InstructorService {
    DataResult<CreateInstructorResponse> add(CreateInstructorRequest request);

    DataResult<Void> delete(int id);

    DataResult<Void> update(CreateInstructorRequest request);

    DataResult<List<GetAllInstructorResponse>> getAll();

    DataResult<GetInstructorResponse> getById(int id);
}
