package com.bootcampProject.business.requests.create.bootcampState;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBootcampStateRequest {
    @Positive
    private int id;

    @NotEmpty(message = "Name cannot be empty...")
    private String name;
}
