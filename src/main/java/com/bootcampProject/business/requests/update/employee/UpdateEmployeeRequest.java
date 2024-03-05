package com.bootcampProject.business.requests.update.employee;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEmployeeRequest {
    @Positive
    private int id;

    @NotEmpty(message = "First name cannot be empty...")
    private String firstName;

    @NotEmpty(message = "Last name cannot be empty...")
    private String lastName;

    @NotEmpty(message = "Email cannot be empty...")
    @Email(message = "Invalid email format...")
    private String email;

    @NotEmpty(message = "Username cannot be empty...")
    @Size(min = 4, max = 20, message = "Username must be between 4 and 20 characters")
    private String username;

    @NotEmpty(message = "Password cannot be empty...")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$",
            message = "Password must contain at least 8 characters, one uppercase letter, one lowercase letter, one digit, and one special character...")
    private String password;

    @NotEmpty(message = "National identity cannot be empty")
    @Size(min = 11, max = 11, message = "National identity must be 11 characters...")
    private String nationalIdentity;


    @NotNull(message = "Date of birth cannot be null...")
    @PastOrPresent(message = "Date of birth must be in the past or present")
    private Date dateOfBirth;

    @NotEmpty(message = "Position cannot be empty...")
    private String position;
}
