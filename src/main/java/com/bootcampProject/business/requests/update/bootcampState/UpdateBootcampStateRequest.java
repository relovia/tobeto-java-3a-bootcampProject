package com.bootcampProject.business.requests.update.bootcampState;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBootcampStateRequest {
    @Positive
    private int id;

    @NotEmpty(message = "Name cannot be empty...")
    private String name;
}
