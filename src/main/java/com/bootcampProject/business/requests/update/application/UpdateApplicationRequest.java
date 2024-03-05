package com.bootcampProject.business.requests.update.application;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateApplicationRequest {
    @Positive
    private int id;

    @NotNull(message = "applicantId cannot be null...")
    private int applicantId;

    @NotNull(message = "bootcampId cannot be null...")
    private int bootcampId;

    @NotNull(message = "applicationStateId cannot be null...")
    private int applicationStateId;
}
