package com.bootcampProject.business.requests.create.application;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateApplicationRequest {
    @NotNull(message = "applicantId cannot be null...")
    private int applicantId;

    @NotNull(message = "bootcampId cannot be null...")
    private int bootcampId;

    @NotNull(message = "applicationStateId cannot be null...")
    private int applicationStateId;
}
