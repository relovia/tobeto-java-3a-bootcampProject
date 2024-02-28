package com.bootcampProject.business.responses.get.blacklist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetBlacklistResponse {
    private int id;
    private String reason;
    private LocalDateTime date;
    private int applicant_id;
}
