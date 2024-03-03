package com.bootcampProject.business.concretes;

import com.bootcampProject.business.abstracts.BlacklistService;
import com.bootcampProject.business.constants.BlacklistMessages;
import com.bootcampProject.business.requests.create.blacklist.CreateBlacklistRequest;
import com.bootcampProject.business.responses.create.blacklist.CreateBlacklistResponse;
import com.bootcampProject.business.responses.get.blacklist.GetAllBlacklistResponse;
import com.bootcampProject.business.responses.get.blacklist.GetBlacklistResponse;
import com.bootcampProject.core.utilities.mapping.ModelMapperService;
import com.bootcampProject.core.utilities.paging.PageDto;
import com.bootcampProject.core.utilities.results.DataResult;
import com.bootcampProject.core.utilities.results.Result;
import com.bootcampProject.core.utilities.results.SuccessDataResult;
import com.bootcampProject.core.utilities.results.SuccessResult;
import com.bootcampProject.dataAccess.abstracts.BlacklistRepository;
import com.bootcampProject.entities.concretes.Blacklist;
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
public class BlacklistManager implements BlacklistService {
    private final BlacklistRepository blacklistRepository;
    private final ModelMapperService mapperService;

    @Autowired
    public BlacklistManager(BlacklistRepository blacklistRepository, ModelMapperService mapperService) {
        this.blacklistRepository = blacklistRepository;
        this.mapperService = mapperService;
    }

    @Override
    public DataResult<CreateBlacklistResponse> add(CreateBlacklistRequest request) {
        Blacklist blacklist = mapperService.forResponse().map(request, Blacklist.class);
        blacklist.setCreatedDate(LocalDateTime.now());
        blacklistRepository.save(blacklist);

        CreateBlacklistResponse response = mapperService.forResponse().map(blacklist, CreateBlacklistResponse.class);
        return new SuccessDataResult<>(response, BlacklistMessages.blacklistAdded);
    }

    @Override
    public Result delete(int id) {
        blacklistRepository.getById(id);
        return new SuccessResult(BlacklistMessages.blacklistDeleted);
    }

    @Override
    public Result update(CreateBlacklistRequest request) {
        int blacklistId = request.getId();
        Blacklist existingBlacklist = blacklistRepository.findById(blacklistId).orElse(null);
        if (existingBlacklist == null) {
            // id not found
            return new SuccessResult(BlacklistMessages.blacklistNotFound);
        }
        mapperService.forRequest().map(request, existingBlacklist);
        blacklistRepository.save(existingBlacklist);
        return new SuccessResult(BlacklistMessages.blacklistUpdated);
    }

    @Override
    public DataResult<List<GetAllBlacklistResponse>> getAll() {
        List<Blacklist> blacklists = blacklistRepository.findAll();
        List<GetAllBlacklistResponse> blacklistResponses = blacklists.stream()
                .map(blacklist -> mapperService.forResponse().map(blacklist, GetAllBlacklistResponse.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(blacklistResponses, BlacklistMessages.blacklistsListed);
    }

    @Override
    public DataResult<GetBlacklistResponse> getById(int id) {
        Blacklist blacklist = blacklistRepository.getById(id);
        GetBlacklistResponse response = mapperService.forResponse().map(blacklist, GetBlacklistResponse.class);
        return new SuccessDataResult<>(response, BlacklistMessages.blacklistListed);
    }

    @Override
    public DataResult<List<GetAllBlacklistResponse>> getAllPage(PageDto pageDto) {
        Sort sort = Sort.by(Sort.Direction.fromString(pageDto.getSortDirection()), pageDto.getSortBy());
        Pageable pageable = PageRequest.of(pageDto.getPageNumber(), pageDto.getPageSize(), sort);
        Page<Blacklist> blacklists = blacklistRepository.findAll(pageable);
        List<GetAllBlacklistResponse> blacklistPages = blacklists.stream()
                .map(blacklist -> mapperService.forResponse().map(blacklist, GetAllBlacklistResponse.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(blacklistPages, BlacklistMessages.blacklistsListed);
    }

    @Override
    public boolean isBlacklisted(String email) {
        Blacklist blacklist = blacklistRepository.getByUser_Email(email);
        return blacklist != null;
    }
}
