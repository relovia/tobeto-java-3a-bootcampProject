package com.bootcampProject.business.concretes;

import com.bootcampProject.business.abstracts.UserService;
import com.bootcampProject.business.constants.UserMessages;
import com.bootcampProject.business.responses.get.users.GetAllUserResponse;
import com.bootcampProject.business.responses.get.users.GetUserResponse;
import com.bootcampProject.core.aspects.logging.Loggable;
import com.bootcampProject.core.utilities.mapping.ModelMapperService;
import com.bootcampProject.core.utilities.paging.PageDto;
import com.bootcampProject.core.utilities.results.DataResult;
import com.bootcampProject.core.utilities.results.SuccessDataResult;
import com.bootcampProject.dataAccess.abstracts.UserRepository;
import com.bootcampProject.entities.concretes.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserManager implements UserService {
    private final UserRepository userRepository;
    private final ModelMapperService mapperService;

    @Autowired
    public UserManager(UserRepository userRepository, ModelMapperService mapperService) {
        this.userRepository = userRepository;
        this.mapperService = mapperService;
    }

    @Override
    @Loggable
    public DataResult<List<GetAllUserResponse>> getAll() {
        List<User> users = userRepository.findAll();
        List<GetAllUserResponse> userResponses = users.stream()
                .map(user -> mapperService.forResponse().map(user, GetAllUserResponse.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(userResponses, UserMessages.usersListed);
    }

    @Override
    @Loggable
    public DataResult<GetUserResponse> getByEmail(String email) {
        User user = userRepository.getByEmail(email);
        if (user != null) {
            GetUserResponse response = mapperService.forResponse().map(user, GetUserResponse.class);
            return new SuccessDataResult<>(response, UserMessages.userEmail);
        } else {
            return new SuccessDataResult<>(null, UserMessages.userNotFound);
        }
    }

    @Override
    public DataResult<List<GetAllUserResponse>> getAllPage(PageDto pageDto) {
        Sort sort = Sort.by(Sort.Direction.fromString(pageDto.getSortDirection()), pageDto.getSortBy());
        Pageable pageable = PageRequest.of(pageDto.getPageNumber(), pageDto.getPageSize(), sort);
        Page<User> users = userRepository.findAll(pageable);
        List<GetAllUserResponse> userPages = users.stream()
                .map(user -> mapperService.forResponse().map(users, GetAllUserResponse.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(userPages, UserMessages.usersListed);
    }
}
