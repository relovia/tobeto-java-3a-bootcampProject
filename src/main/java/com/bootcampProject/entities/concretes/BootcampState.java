package com.bootcampProject.entities.concretes;

import com.bootcampProject.core.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="bootcamp_states")
@PrimaryKeyJoinColumn(name = "user_id")
public class BootcampState extends BaseEntity<Integer> {
    @Column(name = "name")
    private String name;
}
