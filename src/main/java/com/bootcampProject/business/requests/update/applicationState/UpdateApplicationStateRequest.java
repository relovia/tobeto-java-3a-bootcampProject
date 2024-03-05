package com.bootcampProject.business.requests.update.applicationState;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateApplicationStateRequest {
    @Positive
    private int id;

    @NotEmpty(message = "Name cannot be empty...")
    private String name;
}
