package com.bootcampProject.dataAccess.abstracts;

import com.bootcampProject.entities.concretes.ApplicationState;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationStateRepository extends JpaRepository<ApplicationState, Integer> {
}
