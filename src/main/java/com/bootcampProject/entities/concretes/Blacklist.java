package com.bootcampProject.entities.concretes;

import com.bootcampProject.core.entities.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

public class Blacklist extends BaseEntity<Integer> {
    @Column(name="reason")
    private String reason;

    @Column(name="date")
    private LocalDateTime date;

    @ManyToOne(cascade = CascadeType.ALL)
    @Column(name = "applicant_id")
    private Applicant applicant;
}
