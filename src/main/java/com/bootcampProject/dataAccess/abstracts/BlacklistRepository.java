package com.bootcampProject.dataAccess.abstracts;

import com.bootcampProject.entities.concretes.Blacklist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlacklistRepository extends JpaRepository<Blacklist, Integer> {
}
