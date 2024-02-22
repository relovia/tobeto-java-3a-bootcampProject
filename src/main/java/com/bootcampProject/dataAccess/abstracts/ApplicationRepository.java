package com.bootcampProject.dataAccess.abstracts;

import com.bootcampProject.entities.concretes.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {
}
