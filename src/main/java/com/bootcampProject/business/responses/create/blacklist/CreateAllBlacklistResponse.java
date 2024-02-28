package com.bootcampProject.business.responses.create.blacklist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAllBlacklistResponse {
    private int id;
    private String reason;
    private LocalDateTime date;
    private int applicant_id;
}
