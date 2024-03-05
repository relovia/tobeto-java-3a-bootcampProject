package com.bootcampProject.business.requests.update.bootcamp;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBootcampRequest {
    @Positive
    private int id;

    @NotEmpty(message = "Name cannot be empty...")
    private String name;

    @NotNull(message = "instructorId cannot be null...")
    private int instructorId;

    @NotNull(message = "Start date cannot be null...")
    private LocalDateTime startDate;

    @NotNull(message = "End date cannot be null...")
    private LocalDateTime endDate;

    @NotNull(message = "bootcampStateId cannot be null...")
    private int bootcampStateId;
}
