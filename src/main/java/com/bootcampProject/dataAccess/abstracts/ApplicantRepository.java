package com.bootcampProject.dataAccess.abstracts;

import com.bootcampProject.entities.concretes.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicantRepository extends JpaRepository<Applicant, Integer> {
    Applicant getByEmail(String email);

    Applicant getByUsername(String username);
}
