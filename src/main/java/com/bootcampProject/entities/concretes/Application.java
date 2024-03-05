package com.bootcampProject.entities.concretes;

import com.bootcampProject.core.entities.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "applications")
public class Application extends BaseEntity<Integer> {
    @ManyToOne
    @JoinColumn(name = "applicant_id")
    private Applicant applicant;

    @ManyToOne
    @JoinColumn(name = "bootcamp_id")
    private Bootcamp bootcamp;

    @ManyToOne
    @JoinColumn(name = "applicationState_id")
    private ApplicationState applicationState;
}
