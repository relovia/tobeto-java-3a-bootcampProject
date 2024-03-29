package com.bootcampProject.dataAccess.abstracts;

import com.bootcampProject.entities.concretes.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor, Integer> {
    Instructor getByEmail(String email);

    Instructor getByUsername(String username);
}
