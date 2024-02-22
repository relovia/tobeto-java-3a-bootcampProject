package com.bootcampProject.dataAccess.abstracts;

import com.bootcampProject.entities.concretes.BootcampState;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BootcampStateRepository extends JpaRepository<BootcampState, Integer> {
}
