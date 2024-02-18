package com.bootcampProject.dataAccess.abstracts;

import com.bootcampProject.entities.concretes.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User getById(int id);
    User getByEmail(String email);
}
