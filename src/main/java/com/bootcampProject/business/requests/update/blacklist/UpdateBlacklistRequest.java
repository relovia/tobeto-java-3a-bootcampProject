package com.bootcampProject.business.requests.update.blacklist;

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
public class UpdateBlacklistRequest {
    @Positive
    private int id;

    @NotEmpty(message = "Reason cannot be empty...")
    private String reason;

    private LocalDateTime date;

    @NotNull(message = "applicant_id cannot be null...")
    private int applicant_id;
}
