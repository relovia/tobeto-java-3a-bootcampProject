package com.bootcampProject.entities.concretes;

import lombok.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="employees")
@EqualsAndHashCode(callSuper = true)
public class Employee extends User {
    @Column(name = "position")
    private String position;
}