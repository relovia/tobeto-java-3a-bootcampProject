package com.bootcampProject.business.requests.create.instructor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateInstructorRequest {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String username;
    private String nationalIdentity;
    private Date dateOfBirth;
    private String companyName;
}
