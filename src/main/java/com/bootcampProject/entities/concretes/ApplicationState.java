package com.bootcampProject.entities.concretes;

import com.bootcampProject.core.entities.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="application_states")
public class ApplicationState extends BaseEntity<Integer> {
    @Column(name = "name")
    private String name;
}
