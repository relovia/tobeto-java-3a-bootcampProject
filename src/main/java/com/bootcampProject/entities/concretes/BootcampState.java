package com.bootcampProject.entities.concretes;

import com.bootcampProject.core.entities.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bootcamp_states")
public class BootcampState extends BaseEntity<Integer> {
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "bootcampState")
    List<Bootcamp> bootcamps;
}
