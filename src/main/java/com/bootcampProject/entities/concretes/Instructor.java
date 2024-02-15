package com.bootcampProject.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="instructors")
@EqualsAndHashCode(callSuper = true)
public class Instructor extends User {
    @Column(name = "companyName")
    private String companyName;
}