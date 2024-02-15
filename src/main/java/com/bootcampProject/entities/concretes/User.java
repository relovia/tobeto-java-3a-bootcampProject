package com.bootcampProject.entities.concretes;

import com.bootcampProject.core.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "users")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends BaseEntity<Integer> {
    @Column(name = "username")
    private String username;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "dateOfBirth")
    private String dateOfBirth;

    @Column(name = "nationalIdentity")
    private String nationalIdentity;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;
}