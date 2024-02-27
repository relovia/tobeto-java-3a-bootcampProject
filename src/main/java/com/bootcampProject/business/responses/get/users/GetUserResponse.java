package com.bootcampProject.business.responses.get.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserResponse {
    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDateTime createdDate;
}
