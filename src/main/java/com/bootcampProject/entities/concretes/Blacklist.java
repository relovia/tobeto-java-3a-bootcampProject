package com.bootcampProject.entities.concretes;

import com.bootcampProject.core.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "blacklists")
@EqualsAndHashCode(callSuper = true)
public class Blacklist extends BaseEntity<Integer> {
    @Column(name="reason")
    private String reason;

    @Column(name="date")
    private LocalDateTime date;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "applicant_id")
    private Applicant applicant;
}
