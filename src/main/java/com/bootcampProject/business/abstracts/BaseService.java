package com.bootcampProject.business.abstracts;

public interface BaseService {
    void checkIfUserExists(String email);

    void checkIfUsernameExists(String username);
}
