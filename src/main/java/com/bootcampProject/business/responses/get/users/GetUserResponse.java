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
    private String userUsername;
    private String userFirstName;
    private String useLastName;
    private String userDateOfBirth;
    private String userNationalIdentity;
    private String userEmail;
    private String userPassword;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private LocalDateTime deletedDate;
}
