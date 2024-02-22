package com.bootcampProject.dataAccess.abstracts;

import com.bootcampProject.entities.concretes.Bootcamp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BootcampRepository extends JpaRepository<Bootcamp, Integer> {
}
