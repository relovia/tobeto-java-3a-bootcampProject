package com.bootcampProject.business.abstracts;

import com.bootcampProject.business.requests.create.blacklist.CreateBlacklistRequest;
import com.bootcampProject.business.responses.create.blacklist.CreateBlacklistResponse;
import com.bootcampProject.business.responses.get.blacklist.GetAllBlacklistResponse;
import com.bootcampProject.business.responses.get.blacklist.GetBlacklistResponse;
import com.bootcampProject.core.utilities.paging.PageDto;
import com.bootcampProject.core.utilities.results.DataResult;
import com.bootcampProject.core.utilities.results.Result;

import java.util.List;

public interface BlacklistService {
    DataResult<CreateBlacklistResponse> add(CreateBlacklistRequest request);
    Result delete(int id);
    Result update(CreateBlacklistRequest request);
    DataResult<List<GetAllBlacklistResponse>> getAll();
    DataResult<GetBlacklistResponse> getById(int id);
    DataResult<List<GetAllBlacklistResponse>> getAllPage(PageDto pageDto);
    boolean isBlacklisted(String email);

}
