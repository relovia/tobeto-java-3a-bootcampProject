package com.bootcampProject.business.abstracts;

import com.bootcampProject.business.requests.create.instructor.CreateInstructorRequest;
import com.bootcampProject.business.requests.update.instructor.UpdateInstructorRequest;
import com.bootcampProject.business.responses.create.instructors.CreateInstructorResponse;
import com.bootcampProject.business.responses.get.instructors.GetAllInstructorResponse;
import com.bootcampProject.business.responses.get.instructors.GetInstructorResponse;
import com.bootcampProject.core.utilities.paging.PageDto;
import com.bootcampProject.core.utilities.results.DataResult;
import com.bootcampProject.core.utilities.results.Result;

import java.util.List;

public interface InstructorService {
    DataResult<CreateInstructorResponse> add(CreateInstructorRequest request);

    Result delete(int id);

    Result update(UpdateInstructorRequest request);

    DataResult<List<GetAllInstructorResponse>> getAll();

    DataResult<GetInstructorResponse> getById(int id);

    DataResult<List<GetAllInstructorResponse>> getAllPage(PageDto pageDto);

}
