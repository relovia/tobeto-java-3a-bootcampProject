package com.bootcampProject.entities.concretes;

import jakarta.persistence.PrimaryKeyJoinColumn;
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
@PrimaryKeyJoinColumn(name = "user_id")
public class Employee extends User {
    @Column(name = "position")
    private String position;
}