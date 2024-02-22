package com.bootcampProject.business.responses.create.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAllUserResponse {
    private int id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDateTime createdDate;
}
