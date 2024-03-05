package com.bootcampProject.business.requests.create.applicationState;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateApplicationStateRequest {
    @NotEmpty(message = "Name cannot be empty...")
    private String name;
}
