package com.bootcampProject.business.concretes;

import com.bootcampProject.business.abstracts.UserService;
import com.bootcampProject.business.requests.create.user.CreateUserRequest;
import com.bootcampProject.business.responses.create.users.CreateUserResponse;
import com.bootcampProject.business.responses.get.users.GetAllUserResponse;
import com.bootcampProject.business.responses.get.users.GetUserResponse;
import com.bootcampProject.core.utilities.mapping.ModelMapperService;
import com.bootcampProject.dataAccess.abstracts.UserRepository;
import com.bootcampProject.entities.concretes.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    public CreateUserResponse add(CreateUserRequest request) {
        User user = mapperService.forRequest().map(request, User.class);
        user.setCreatedDate(LocalDateTime.now());
        userRepository.save(user);

        CreateUserResponse response = mapperService.forResponse()
                .map(user, CreateUserResponse.class);
        return response;
    }

    @Override
    public void delete(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public void update(CreateUserRequest request) {
    }

    @Override
    public List<GetAllUserResponse> getAll() {
        List<User> users = userRepository.findAll();
        return  users.stream()
                .map(user -> mapperService.forResponse().map(user, GetAllUserResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public GetUserResponse getById(int id) {
        User user = userRepository.getById(id);
        GetUserResponse response = mapperService.forResponse().map(user, GetUserResponse.class);
        return response;
    }

    @Override
    public GetUserResponse getByEmail(String email) {
        User user = userRepository.getByEmail(email);
        GetUserResponse response = mapperService.forResponse().map(user, GetUserResponse.class);
        return response;
    }
}
