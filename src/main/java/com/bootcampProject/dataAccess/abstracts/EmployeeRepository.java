package com.bootcampProject.dataAccess.abstracts;

import com.bootcampProject.entities.concretes.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee getByEmail(String email);

    Employee getByUsername(String username);
}
